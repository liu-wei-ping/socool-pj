/****/
package com.socool.site.biz.crawler.ziroom;

import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.socool.site.biz.crawler.AbstractCrawlerStrategy;

import edu.uci.ics.crawler4j.url.WebURL;

/**
 * @author liuwp
 * @date 2016年12月9日
 */
@Slf4j
public class ZiroomCrawler extends AbstractCrawlerStrategy {

	@Override
	public void crawlerWeb(final String webUrl) {
		// TODO Auto-generated method stub

	}

	@Override
	public void storeData(final Document doc) {
		final Elements contents = doc.select("li[class=clearfix]");
		for (final Element c : contents) {
			// 图片
			final String img = c.select(".img img").first().attr("src");
			System.out.println("图片：" + img);

			// 地址
			final Element txt = c.select("div[class=txt]").first();
			final String arr1 = txt.select("h3 a").first().text();
			final String arr2 = txt.select("h4 a").first().text();
			final String arr3 = txt.select("div[class=detail]").first().text();

			final String arr = arr1.concat(arr1 + ",").concat(arr2 + ",")
					.concat(arr3);
			System.out.println("地址：" + arr);
			// 说明
			final String rank = txt.select("p").first().text();
			System.out.println("说明：" + rank);

			// 价格
			final String pirce = c.select("p[class=price]").first().text();
			System.out.println(pirce);
		}
	}

	@Override
	protected void storelinks(final Set<WebURL> links) {
		for (final WebURL webURL : links) {
			final String url = webURL.getURL();
			log.info("url: {}", url);
		}

	}

}
