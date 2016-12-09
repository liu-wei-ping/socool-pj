/****/
package com.socool.site.biz.crawler.basic;

import java.util.Set;
import java.util.regex.Pattern;

import org.apache.http.Header;

import com.socool.site.biz.utils.Base64Util;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * @author liuwp
 * @date 2016年12月8日
 */
public class BasicCrawler extends WebCrawler {

	private static final Pattern IMAGE_EXTENSIONS = Pattern
			.compile(".*\\.(bmp|gif|jpg|png)$");

	/**
	 * You should implement this function to specify whether the given url
	 * should be crawled or not (based on your crawling logic).
	 */
	@Override
	public boolean shouldVisit(final Page referringPage, final WebURL url) {
		final String href = url.getURL().toLowerCase();
		// Ignore the url if it has an extension that matches our defined set of
		// image extensions.
		if (IMAGE_EXTENSIONS.matcher(href).matches()) {
			return false;
		}

		// Only accept the url if it is in the "www.ics.uci.edu" domain and
		// protocol is "http".
		return href.startsWith("http://www.ziroom.com/");
		// return href.startsWith("http://www.ics.uci.edu/");
	}

	/**
	 * This function is called when a page is fetched and ready to be processed
	 * by your program.
	 */
	@Override
	public void visit(final Page page) {
		final int docid = page.getWebURL().getDocid();
		final String url = page.getWebURL().getURL();
		final String domain = page.getWebURL().getDomain();
		final String path = page.getWebURL().getPath();
		final String subDomain = page.getWebURL().getSubDomain();
		final String parentUrl = page.getWebURL().getParentUrl();
		final String anchor = page.getWebURL().getAnchor();
		logger.debug("Docid: {}", docid);
		logger.info("URL: {}", url);
		logger.debug("Domain: '{}'", domain);
		logger.debug("Sub-domain: '{}'", subDomain);
		logger.debug("Path: '{}'", path);
		logger.debug("Parent page: {}", parentUrl);
		logger.debug("Anchor text: {}", anchor);
		System.out.println("Anchor text: {}" + anchor);
		System.out.println("domain: {}" + domain);
		System.out.println("URL: {}" + url);
		System.out.println("Sub-domain: '{}'" + subDomain);
		System.out.println("Path: '{}'" + path);
		if (page.getParseData() instanceof HtmlParseData) {
			final HtmlParseData htmlParseData = (HtmlParseData) page
					.getParseData();
			final String text = htmlParseData.getText();
			final String html = htmlParseData.getHtml();
			final Set<WebURL> links = htmlParseData.getOutgoingUrls();
			// System.out.println(html);
			logger.debug("Text length: {}", text.length());
			logger.debug("Html length: {}", html.length());
			logger.debug("Number of outgoing links: {}", links.size());
		}

		final Header[] responseHeaders = page.getFetchResponseHeaders();
		if (responseHeaders != null) {
			logger.debug("Response headers:");
			for (final Header header : responseHeaders) {
				System.out.println(header.getName() + "<---->"
						+ header.getValue());
				logger.info("\t{}: {}", header.getName(),
						Base64Util.decodeBase64(header.getValue()));
			}
		}

		logger.debug("=============");
	}
}
