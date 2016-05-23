/*** */
package com.socool.site.condition;

import lombok.Data;

/**
 * @author mr.lwp
 * @see 2016年5月22日
 */
@Data
public class PageCondition {

	private int limit;// 读取数据条数
	private int offset;// 从第一offset开始读取数据
	private int page;// 第page 页数
	// private int rows;// 总数据

	private int totalPage;// 总页数

	/**
	 * 默认第一页,默认每页查询10条数据
	 */
	public PageCondition() {
		this.page = 1;
		this.limit = 10;
		this.offset = (this.page - 1) * this.limit;
	}

	public PageCondition(final int page, final int limit) {
		this.page = page > 0 ? page : 1;
		this.limit = limit;
		this.offset = (this.page - 1) * limit;
	}

	public int getTotalPage(final int rows) {
		this.totalPage = 0;
		if (this.limit > 0) {
			this.totalPage = rows % this.limit > 0 ? rows / this.limit + 1 : rows / this.limit;
		}
		return this.totalPage;
	}

	// public PageCondition(final int rows) {
	// if (this.limit > 0) {
	// this.totalPage = rows % this.limit > 0 ? rows / this.limit + 1 : rows /
	// this.limit;
	// }
	// }
}
