/****/
package com.socool.site.biz.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * @author liuwp
 * @date 2016年12月7日
 */
public class CookieUtil {

	public static Cookie createCookie(final String name, final String value) {
		return createCookie(name, value, null, true, "/", null);
	}

	public static Cookie createCookie(final String name, final String value,
			final boolean isBase64) {
		return createCookie(name, value, null, isBase64, "/", null);
	}

	public static Cookie createCookie(final String name, final String value,
			final int expires) {
		return createCookie(name, value, expires, true, "/", null);
	}

	public static Cookie createCookie(final String name, final String value,
			final int expires, final boolean isBase64) {
		return createCookie(name, value, expires, isBase64, "/", null);
	}

	public static Cookie createCookie(final String name, String value,
			final Integer expires, final boolean isBase64, final String path,
			final String domain) {
		value = isBase64 ? Base64Util.encodeBase64(value.trim()) : value.trim();
		final Cookie cookie = new Cookie(name.trim(), value);
		cookie.setPath(path);
		if (expires != null) {
			cookie.setMaxAge(expires);
		}
		if (StringUtils.isNotBlank(domain)) {
			cookie.setDomain(domain);
		}
		return cookie;
	}

	public static void destroyCookie(final HttpServletRequest request,
			final HttpServletResponse response, final String name) {
		final Cookie[] cookies = request.getCookies();
		if (null == cookies) {

		} else {
			for (final Cookie cookie : cookies) {
				if (name.equalsIgnoreCase(cookie.getName())) {
					cookie.setValue(null);
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
					break;
				}
			}
		}
	}

	public static Cookie getCookie(final HttpServletRequest request,
			final String name) {
		final Cookie[] cookies = request.getCookies();
		if (null == cookies) {
			return null;
		} else {
			for (final Cookie cookie : cookies) {
				if (name.equalsIgnoreCase(cookie.getName())) {
					return cookie;
				}
			}
		}
		return null;
	}

	public static String getCookieValue(final HttpServletRequest request,
			final String name) {
		return getCookieValue(request, name, true);
	}

	public static String getCookieValue(final HttpServletRequest request,
			final String name, final boolean isBase64) {
		final Cookie[] cookies = request.getCookies();
		if (null == cookies) {
			return null;
		} else {
			for (final Cookie cookie : cookies) {
				if (name.equalsIgnoreCase(cookie.getName())) {
					try {
						if (isBase64) {
							return Base64Util.decodeBase64(cookie.getValue());
						} else {
							return URLDecoder
									.decode(cookie.getValue(), "UTF-8");
						}
					} catch (final UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}
}
