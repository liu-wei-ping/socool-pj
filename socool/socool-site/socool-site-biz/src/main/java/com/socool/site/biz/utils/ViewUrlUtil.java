/****/
package com.socool.site.biz.utils;

/**
 * @author liuwp
 * @date 2016年5月20日
 */
public class ViewUrlUtil {
	public static String getViewUrl(final String type, final String view) {
		return type.concat("/").concat(view);
	}
}
