/****/
package com.socool.site.entry.stock;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.socool.site.entry.BaseEntry;

/**
 * @author liuwp
 * @date 2016年11月24日
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class StockHqEntry extends BaseEntry {

	/*** 卖一报价 */
	private BigDecimal auctionprice;
	/*** 昨日收盘价格 */
	private BigDecimal closingprice;
	private String code;
	/*** 买一报价 */
	private BigDecimal competitiveprice;
	/*** 当前价格 */
	private BigDecimal currentprice;
	/*** 日期 */
	private String date;
	/*** 最高价格 */
	private BigDecimal hprice;
	/*** 最低价格 */
	private BigDecimal lprice;
	private String name;
	/*** 开盘价格 */
	private BigDecimal openningprice;
	/*** */
	private String time;
	/*** 成交量股 */
	private int totalnumber;
	/*** 成交额 */
	private BigDecimal turnover;
}
