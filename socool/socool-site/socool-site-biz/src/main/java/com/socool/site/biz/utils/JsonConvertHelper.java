/**
 *
 */
package com.socool.site.biz.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.json.JSONObject;

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

	/**
	 *
	 * @param jsonStr
	 * @param c
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> getListFromJson(final String jsonStr,
			final Class<T> c) {

		final List<T> list = new ArrayList<T>();
		try {

			final List<LinkedHashMap<String, Object>> mapList = objectMapper
					.readValue(jsonStr, List.class);
			for (int i = 0; i < mapList.size(); i++) {
				// 转小写
				final Map<String, Object> map3 = new CaseInsensitiveMap(
						mapList.get(i));
				final String mapStr = map2Json(map3);
				final T t = jsonToObject(mapStr, c);
				list.add(t);
				// final StockInfoBo bo = new StockInfoBo();
				// BeanUtils.populate(bo, map3);
				// System.out.println(bo);
				// final Set<String> set = map.keySet();
				// for (final Iterator<String> it = set.iterator();
				// it.hasNext();) {
				// final String key = it.next();
				// System.out.println(key + ":" + map.get(key));
				//
				// }

			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return list;
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

	/**
	 * map Convert json
	 *
	 * @param data
	 * @return
	 */
	public static String map2Json(final Map<?, ?> data) {
		final JSONObject json = new JSONObject(data);
		// final StringBuffer jsonStr = new StringBuffer();
		// for (final Entry<?, ?> pair : data.entrySet()) {
		// jsonStr.append(pair.getKey() + ":");
		// jsonStr.append(pair.getValue());
		//
		// }
		// return jsonStr.toString();
		return String.valueOf(json);
	}

	public static Object map2Object(final Map map, final Class type) {
		Object obj = null;
		try {
			final BeanInfo beanInfo = Introspector.getBeanInfo(type);
			obj = type.newInstance(); // 创建 JavaBean 对象
			// 给 JavaBean 对象的属性赋值
			final PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				final PropertyDescriptor descriptor = propertyDescriptors[i];
				final String propertyName = descriptor.getName();
				if (map.containsKey(propertyName)) {
					// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
					final Object value = map.get(propertyName);
					final Object[] args = new Object[1];
					args[0] = value;
					descriptor.getWriteMethod().invoke(obj, args);
				}
			}
		} catch (final IntrospectionException | IllegalAccessException
				| InstantiationException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return obj;
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
