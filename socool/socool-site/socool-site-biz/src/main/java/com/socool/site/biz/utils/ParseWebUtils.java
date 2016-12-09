/****/
package com.socool.site.biz.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author liuwp
 * @date 2016年12月9日
 */
public class ParseWebUtils {

	public static void parseHtml(final String html) {
		final Document doc = Jsoup.parse(html);
	}
}
