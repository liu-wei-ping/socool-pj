/**
 *
 */
package com.socool.site.biz.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON 转换工具类
 *
 * @author 刘伟平<liuweiping@ wcp.sina.com>
 * @since 2016年3月7日 下午2:33:45
 * @version 1.0
 */
public class JsonConvertHelper {
	final static ObjectMapper objectMapper = new ObjectMapper();

	public static <T> String getJsonFromObjects(final List<T> sourceList) {
		final StringWriter str = new StringWriter();
		try {
			objectMapper.writeValue(str, sourceList);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return str.toString();
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> getObjectsFromJson(final String jsonStr,
			final Class<?> collectionClass, final Class<T>... parameterClasses) {
		final JavaType javaType = getCollectionType(collectionClass,
				List.class, parameterClasses);
		List<T> tagaList = null;
		try {
			tagaList = (List<T>) objectMapper.readValue(jsonStr, javaType);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return tagaList;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonToMap(final String json) {
		Map<String, Object> map = null;
		try {
			map = objectMapper.readValue(json, Map.class);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public static <T> T jsonToObject(final String json, final Class<T> c) {
		T t = null;
		try {
			t = objectMapper.readValue(json, c);
		} catch (final JsonParseException e) {
			e.printStackTrace();
		} catch (final JsonMappingException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return t;
	}

	public static <T> String ObjToJsonStr(final T source) {
		String jsonStr = "";
		try {
			jsonStr = objectMapper.writeValueAsString(source);
		} catch (final JsonGenerationException e) {
			e.printStackTrace();
		} catch (final JsonMappingException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}

	/**
	 * Map Convert String
	 *
	 * @param data
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String toQueryString(final Map<?, ?> data)
			throws UnsupportedEncodingException {
		final StringBuffer queryString = new StringBuffer();
		for (final Entry<?, ?> pair : data.entrySet()) {
			queryString.append(pair.getKey() + "=");
			queryString.append(URLEncoder.encode((String) pair.getValue(),
					"UTF-8") + "&");
		}
		if (queryString.length() > 0) {
			queryString.deleteCharAt(queryString.length() - 1);
		}
		return queryString.toString();
	}

	private static JavaType getCollectionType(final Class<?> collectionClass,
			final Class<?> parametersFor, final Class<?>... parameterClasses) {
		return objectMapper.getTypeFactory().constructParametrizedType(
				collectionClass, parametersFor, parameterClasses);
	}
}
