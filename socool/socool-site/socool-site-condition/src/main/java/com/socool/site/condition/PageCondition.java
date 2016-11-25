/*** */
package com.socool.site.condition;

import lombok.Data;

/**
 * @author mr.lwp
 * @see 2016年5月22日
 */
@Data
public class PageCondition {
	@Deprecated
	private int limit;// 读取数据条数
	private int offset;// 从第一offset开始读取数据
	private String order;// 排序的方式（asc、desc）
	private int page; // 第page 页数
	private int rows;// 读取数据条数 easyui
	private String sort;// 排序的字段
	private int totalPage;// 总页数

	/**
	 * 默认第一页,默认每页查询10条数据
	 */
	public PageCondition() {
		this.page = 1;
		this.rows = 10;
		this.offset = (this.page - 1) * this.rows;
	}

	public PageCondition(final int page, final int rows) {
		this.page = page > 0 ? page : 1;
		this.rows = rows;
		this.offset = (this.page - 1) * rows;
	}

	public PageCondition(final PageCondition page) {
		this.page = page.getPage();
		this.rows = page.getRows();
		this.sort = page.getSort();
		this.order = page.getOrder() != null ? page.getOrder() : "asc";
		this.offset = (this.page - 1) * this.rows;
	}

	public int getTotalPage(final int total) {
		this.totalPage = 0;
		if (this.rows > 0) {
			this.totalPage = total % this.rows > 0 ? total / this.rows + 1
					: total / this.rows;
		}
		return this.totalPage;
	}

}
