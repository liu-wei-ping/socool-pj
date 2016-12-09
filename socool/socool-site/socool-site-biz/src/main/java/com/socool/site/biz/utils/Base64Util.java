package com.socool.site.biz.utils;

import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class Base64Util {
	// 解密
	public static String decodeBase64(final String s) {
		byte[] b = null;
		String result = null;
		if (s != null) {
			final BASE64Decoder decoder = new BASE64Decoder();
			try {
				b = decoder.decodeBuffer(s);
				result = new String(b, "utf-8");
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 加密
	public static String encodeBase64(final String str) {
		byte[] b = null;
		String s = null;
		try {
			b = str.getBytes("utf-8");
		} catch (final UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (b != null) {
			s = new BASE64Encoder().encode(b);
		}
		return s;
	}
}
