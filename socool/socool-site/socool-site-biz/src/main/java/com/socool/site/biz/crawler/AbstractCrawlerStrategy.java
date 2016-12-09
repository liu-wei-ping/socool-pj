/****/
package com.socool.site.biz.crawler;

import java.util.Set;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * @author liuwp
 * @date 2016年12月9日
 */
@Slf4j
public abstract class AbstractCrawlerStrategy extends WebCrawler implements
		ICrawlerStrategy {
	protected String regex = ".*\\.(bmp|gif|jpg|png)$";

	@Override
	public void onBeforeExit() {
		// TODO Auto-generated method stub
		super.onBeforeExit();
	}

	@Override
	public void onStart() {
		log.info("Start.........");
		super.onStart();
	}

	@Override
	public boolean shouldVisit(final Page referringPage, final WebURL url) {
		final String href = url.getURL().toLowerCase();
		if (Pattern.compile(regex).matcher(href).matches()) {
			return false;
		}
		return true;

	}

	@Override
	public void visit(final Page page) {
		if (page.getParseData() instanceof HtmlParseData) {
			final HtmlParseData htmlParseData = (HtmlParseData) page
					.getParseData();
			final Set<WebURL> links = htmlParseData.getOutgoingUrls();
			storelinks(links);
			final String html = htmlParseData.getHtml();
			final Document doc = Jsoup.parse(html);
			storeData(doc);
		}

	}

	protected abstract void storelinks(final Set<WebURL> links);

	protected abstract void storeData(final Document doc);

}
