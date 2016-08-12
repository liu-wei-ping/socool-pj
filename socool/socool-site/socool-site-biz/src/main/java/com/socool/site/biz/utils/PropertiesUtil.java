package com.socool.site.biz.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	static Properties props = new Properties();

	public static Properties getInstance(final String path) throws IOException {
		props.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(
				path));
		return props;
	}

	public static String getProperty(final String key) {
		String val = "";
		if (props != null) {
			final String prop = props.getProperty(key);
			if (prop != null) {
				val = prop;
			}
		}
		return val;
	}

	private PropertiesUtil() {
	}
}
