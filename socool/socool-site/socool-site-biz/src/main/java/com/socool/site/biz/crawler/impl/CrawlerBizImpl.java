/****/
package com.socool.site.biz.crawler.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csvreader.CsvWriter;
import com.socool.site.biz.crawler.ICrawlerBiz;
import com.socool.site.bo.crawler.CrawlStat;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * @author liuwp
 * @date 2016年12月8日
 */
public class CrawlerBizImpl extends WebCrawler implements ICrawlerBiz {
	/** 爬取数据保存文件路径 */
	private final static String CSV_PATH = "D:\\data\\crawl\\ziroom2.csv";
	/** 爬取匹配原则 */
	private final static Pattern FILTERS = Pattern
			.compile(".*(\\.(css|js|bmp|gif|jpe?g|ico"
					+ "|png|tiff?|mid|mp2|mp3|mp4"
					+ "|wav|avi|mov|mpeg|ram|m4v|pdf"
					+ "|rm|smil|wmv|swf|wma|zip|rar|gz))$");
	/** 爬取link文件路径 */
	private final static String LINK_PATH = "D:\\data\\crawl\\link.csv";
	private final static String LINK_PATH_FOLDER = "D:\\data\\crawl";
	private static Logger logger = LoggerFactory
			.getLogger(CrawlerBizImpl.class);
	private final static String URL_PREFIX = "http://sh.ziroom.com/z/nl/";

	public static void main(final String[] args) {
		System.out.println("-------begin:"
				+ new Timestamp(System.currentTimeMillis()));
		final String crawlStorageFolder = "data/crawl/root";
		final int numberOfCrawlers = 7;

		final CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(crawlStorageFolder);
		config.setPolitenessDelay(1000);
		config.setIncludeBinaryContentInCrawling(false);
		config.setMaxPagesToFetch(50);
		// config.setResumableCrawling(true);
		/*
		 * Instantiate the controller for this crawl.
		 */
		final PageFetcher pageFetcher = new PageFetcher(config);
		final RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		final RobotstxtServer robotstxtServer = new RobotstxtServer(
				robotstxtConfig, pageFetcher);
		CrawlController controller;
		try {
			controller = new CrawlController(config, pageFetcher,
					robotstxtServer);
			/*
			 * For each crawl, you need to add some seed urls. These are the
			 * first URLs that are fetched and then the crawler starts following
			 * links which are found in these pages
			 */
			controller.addSeed("http://sh.ziroom.com/z/nl/");
			// controller.addSeed("http://www.ziroom.com/z/nl/z3-u2.html/");
			// controller.addSeed("http://www.ics.uci.edu/~welling/");
			// controller.addSeed("http://www.ics.uci.edu/");

			/*
			 * Start the crawl. This is a blocking operation, meaning that your
			 * code will reach the line after this only when crawling is
			 * finished.
			 */
			controller.start(CrawlerBizImpl.class, numberOfCrawlers);

			final List<Object> crawlersLocalData = controller
					.getCrawlersLocalData();
			long totalLinks = 0;
			long totalTextSize = 0;
			int totalProcessedPages = 0;
			for (final Object localData : crawlersLocalData) {
				final CrawlStat stat = (CrawlStat) localData;
				totalLinks += stat.getTotalLinks();
				totalTextSize += stat.getTotalTextSize();
				totalProcessedPages += stat.getTotalProcessedPages();
			}

			System.out.println("Aggregated Statistics:");
			System.out.println("\tProcessed Pages: {}" + totalProcessedPages);
			System.out.println("\tTotal Links found: {}" + totalLinks);
			System.out.println("\tTotal Text Size: {}" + totalTextSize);
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private File csv = null;
	private File csv2 = null;
	private CsvWriter cw;
	private CsvWriter cw2;

	CrawlStat myCrawlStat;

	public void dumpMyData() {
		final int id = getMyId();
	}

	@Override
	public Object getMyLocalData() {
		return myCrawlStat;
	}

	@Override
	public void onBeforeExit() {
		dumpMyData();
	}

	/*
	 * 这个方法决定了要抓取的URL及其内容，例子中只允许抓取“http://sh.ziroom.com/z/nl/”这个域的页面,
	 * 不允许.css、.js和多媒体等文件
	 * 
	 * @see edu.uci.ics.crawler4j.crawler.WebCrawler#shouldVisit(edu.uci.ics.
	 * crawler4j.crawler.Page, edu.uci.ics.crawler4j.url.WebURL)
	 */
	@Override
	public boolean shouldVisit(final Page referringPage, final WebURL url) {
		final String href = url.getURL().toLowerCase();

		if (FILTERS.matcher(href).matches() || !href.startsWith(URL_PREFIX)) {
			return false;
		}
		return true;
	}

	/*
	 * 当URL下载完成会调用这个方法。你可以轻松获取下载页面的url, 文本, 链接, html,和唯一id等内容。
	 * 
	 * @see
	 * edu.uci.ics.crawler4j.crawler.WebCrawler#visit(edu.uci.ics.crawler4j.
	 * crawler.Page)
	 */
	@Override
	public void visit(final Page page) {
		final String url = page.getWebURL().getURL();
		// System.out.println("-----------爬取路径：" + url);
		// myCrawlStat.incProcessedPages();
		// System.out.println(page);
		if (page.getParseData() instanceof HtmlParseData) {
			final HtmlParseData htmlParseData = (HtmlParseData) page
					.getParseData();
			System.out.println(htmlParseData.getTitle());
			final Set<WebURL> links = htmlParseData.getOutgoingUrls();

			try {
				linkToCsv(links);
			} catch (final IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			myCrawlStat.incTotalLinks(links.size());
			try {
				myCrawlStat.incTotalTextSize(htmlParseData.getText().getBytes(
						"UTF-8").length);
			} catch (final UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			final String html = htmlParseData.getHtml();

			final Document doc = Jsoup.parse(html);

			final Elements contents = doc.select("li[class=clearfix]");
			System.out.println(contents);
			for (final Element c : contents) {
				// 图片
				final String img = c.select(".img img").first().attr("src");
				System.out.println("图片：" + img);

				// 地址
				final Element txt = c.select("div[class=txt]").first();
				final String arr1 = txt.select("h3 a").first().text();
				final String arr2 = txt.select("h4 a").first().text();
				final String arr3 = txt.select("div[class=detail]").first()
						.text();

				final String arr = arr1.concat(arr1 + ",").concat(arr2 + ",")
						.concat(arr3);
				System.out.println("地址：" + arr);
				// 说明
				final String rank = txt.select("p").first().text();
				System.out.println("说明：" + rank);

				// 价格
				final String pirce = c.select("p[class=price]").first().text();

				try {
					cw = new CsvWriter(new FileWriter(csv, true), ',');
					cw.write(img);
					cw.write(pirce);
					cw.write(arr);
					cw.write(rank);
					cw.endRecord();
					cw.flush();
					cw.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void webCrawler(final String url) {
		// TODO Auto-generated method stub

	}

	public void ZiroomCrawler() throws IOException {
		myCrawlStat = new CrawlStat();
		csv = new File(CSV_PATH);
		csv2 = new File(LINK_PATH);
		final File folder = new File(LINK_PATH_FOLDER);
		// 如果文件夹不存在则创建
		if (!folder.exists() && !folder.isDirectory()) {
			System.out.println("//不存在");
			folder.mkdir();
		}
		if (csv.isFile()) {
			csv.delete();
		}
		if (csv2.isFile()) {
			csv2.delete();
		}
		cw2 = new CsvWriter(new FileWriter(csv2, true), ',');
		cw2.write("请求路径");
		cw2.endRecord();
		cw2.close();
		cw = new CsvWriter(new FileWriter(csv, true), ',');
		cw.write("图片");
		cw.write("价格");
		cw.write("地址");
		cw.write("说明");
		cw.endRecord();
		cw.close();
	}

	private void linkToCsv(final Set<WebURL> links) throws IOException {
		cw2 = new CsvWriter(new FileWriter(csv2, true), ',');
		for (final WebURL webURL : links) {
			cw2.write(webURL.getURL());
		}
		cw2.flush();
		cw2.endRecord();
		cw2.close();

	}
}
