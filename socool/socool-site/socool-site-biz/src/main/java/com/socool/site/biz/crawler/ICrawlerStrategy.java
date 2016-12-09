/****/
package com.socool.site.biz.crawler;

/**
 * @author liuwp
 * @date 2016年12月9日
 */
public interface ICrawlerStrategy {
	/**
	 * @param doc
	 */
	void crawlerWeb(final String webUrl);

}
