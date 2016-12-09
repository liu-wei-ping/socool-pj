/****/
package com.socool.site.bo.crawler;

/**
 * @author liuwp
 * @date 2016年12月8日
 */
public class CrawlStat {
	private long totalLinks;
	private int totalProcessedPages;
	private long totalTextSize;

	public long getTotalLinks() {
		return totalLinks;
	}

	public int getTotalProcessedPages() {
		return totalProcessedPages;
	}

	public long getTotalTextSize() {
		return totalTextSize;
	}

	public void incProcessedPages() {
		this.totalProcessedPages++;
	}

	public void incTotalLinks(final int count) {
		this.totalLinks += count;
	}

	public void incTotalTextSize(final int count) {
		this.totalTextSize += count;
	}

	public void setTotalLinks(final long totalLinks) {
		this.totalLinks = totalLinks;
	}

	public void setTotalProcessedPages(final int totalProcessedPages) {
		this.totalProcessedPages = totalProcessedPages;
	}

	public void setTotalTextSize(final long totalTextSize) {
		this.totalTextSize = totalTextSize;
	}
}
