/**
 * 
 */
package com.socool.site.biz.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author 刘伟平<liuweiping@ wcp.sina.com>
 * @since 2016年3月21日 下午7:34:57
 * @version 1.0
 */
@Slf4j
public class HttpSendUtil {
	private static final String APPLICATION_JSON = "application/json";
	private static final String CONTENT_TYPE_TEXT_JSON = "text/json";
	private static RequestConfig requestConfig = null;
	final static ObjectMapper objectMapper = new ObjectMapper();
	static {
		requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(50000).setConnectTimeout(50000)
				.setSocketTimeout(50000).build();
	}

	/**
	 * 默认编码utf -8 Obtains character set of the entity, if known.
	 * 
	 * @param entity
	 *            must not be null
	 * @return the character set, or null if not found
	 * @throws ParseException
	 *             if header elements cannot be parsed
	 * @throws IllegalArgumentException
	 *             if entity is null
	 */
	public static String getContentCharSet(final HttpEntity entity)
			throws ParseException {
		if (entity == null) {
			throw new IllegalArgumentException("HTTP entity may not be null");
		}
		String charset = null;
		if (entity.getContentType() != null) {
			final HeaderElement values[] = entity.getContentType()
					.getElements();
			if (values.length > 0) {
				final NameValuePair param = values[0]
						.getParameterByName("charset");
				if (param != null) {
					charset = param.getValue();
				}
			}
		}

		if (StringUtils.isEmpty(charset)) {
			charset = "UTF-8";
		}
		return charset;
	}

	/**
	 * Get 请求
	 * 
	 * @param url
	 * @return
	 */
	public static String httpGet(final String url) {
		final CloseableHttpClient httpClient = getCloseableHttpClient();
		final HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
		CloseableHttpResponse response = null;
		String result = null;
		String charset = HTTP.UTF_8;
		try {
			response = httpClient.execute(httpGet);
			final HttpEntity entity = response.getEntity();
			if (null != entity) {
				System.out.println("响应状态码:" + response.getStatusLine());
				System.out
						.println("-------------------------------------------------");
				// System.out.println("响应内容:" + EntityUtils.toString(entity));
				System.out
						.println("-------------------------------------------------");
				charset = getContentCharSet(entity);
				result = EntityUtils.toString(entity, charset);
				EntityUtils.consume(entity);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			closeHttpResponseAndHttpClient(response, httpClient);
		}
		return result;
	}

	/**
	 * Post 请求
	 * 
	 * @param url
	 * @param json
	 *            参数
	 * @return
	 */
	public static String httpPostWithJSON(final String url, final String json) {
		final CloseableHttpClient httpClient = getCloseableHttpClient();
		final HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
		CloseableHttpResponse response = null;
		String result = null;
		String charset = HTTP.UTF_8;
		try {
			// 将JSON进行UTF-8编码,以便传输中文
			final String encoderJson = URLEncoder.encode(json, charset);
			final StringEntity se = new StringEntity(encoderJson);
			se.setContentType(CONTENT_TYPE_TEXT_JSON);
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
					APPLICATION_JSON));
			httpPost.setEntity(se);

			response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				final HttpEntity entity = response.getEntity();
				charset = getContentCharSet(entity);
				result = EntityUtils.toString(entity);
				EntityUtils.consume(entity);
			} else {
				log.warn("请求失败！");
			}
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			closeHttpResponseAndHttpClient(response, httpClient);
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */

	public static String sendPost2(final String url, final String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			final URL realUrl = new URL(url);
			// 打开和URL之间的连接
			final URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("X-QFGJ-MID", "10000001");
			conn.setRequestProperty("merchant_uid", "10000002");
			conn.setRequestProperty("X-QFGJ-RTIME",
					String.valueOf(System.currentTimeMillis()));
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (final IOException e) {
			// log.info("网络连接错误！！");
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	private static void closeHttpResponseAndHttpClient(
			final CloseableHttpResponse httpResponse,
			final CloseableHttpClient client) {
		try {
			if (null != httpResponse) {
				httpResponse.close();
			}
			if (null != client) {
				client.close();
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	private static CloseableHttpClient getCloseableHttpClient() {
		return HttpClients.custom().setDefaultRequestConfig(requestConfig)
				.build();
	}
}
