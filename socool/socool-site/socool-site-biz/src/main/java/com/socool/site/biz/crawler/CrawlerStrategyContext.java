/****/
package com.socool.site.biz.crawler;

/**
 * @author liuwp
 * @date 2016年12月9日
 */
public class CrawlerStrategyContext {
	private final ICrawlerStrategy iCrawlerStrategy;

	public CrawlerStrategyContext(final ICrawlerStrategy iCrawlerStrategy) {
		this.iCrawlerStrategy = iCrawlerStrategy;
	}

	public void crawlerWeb(final String webUrl) {
		this.iCrawlerStrategy.crawlerWeb(webUrl);
	}
}
