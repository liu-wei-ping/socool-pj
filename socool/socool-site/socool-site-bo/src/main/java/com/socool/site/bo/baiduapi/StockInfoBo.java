/****/
package com.socool.site.bo.baiduapi;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.socool.site.bo.BaseBo;

/**
 * @author liuwp
 * @date 2016年8月10日
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class StockInfoBo extends BaseBo implements Serializable {
	/*** */
	private static final long serialVersionUID = 1L;
	/*** 卖一报价 */
	private String auctionprice;
	private String buyfive;
	private String buyfiveprice;
	private String buyfour;
	private String buyfourprice;
	private String buyone;
	private String buyoneprice;
	private String buythree;
	private String buythreeprice;
	private String buytwo;
	private String buytwoprice;
	/*** 昨日收盘价 */
	private String closingprice;
	/*** 股票代码 */
	private String code;
	/*** 买一报价 */
	private String competitiveprice;
	/*** 当前价 */
	private String currentprice;
	/*** 日期 */
	private String date;
	/*** 日k线图 */
	private String dayurl;
	/*** 今日最高价 */
	private String hprice;
	/*** 价格涨幅 */
	private String increase;
	/*** */
	private String increaseStr;
	/*** 今日最低价 */
	private String lprice;
	/*** 分时k线图 */
	private String minurl;
	/*** 月k线图 */
	private String monthurl;
	/*** 股票名称 */
	private String name;
	/*** 开盘价 */
	private String openningprice;
	private String sellfive;
	private String sellfiveprice;
	private String sellfour;
	private String sellfourprice;
	private String sellone;
	private String selloneprice;
	private String sellthree;
	private String sellthreeprice;
	private String selltwo;
	private String selltwoprice;
	/*** 具体时间 */
	private String time;
	/*** 成交量股 */
	private String totalnumber;
	/*** 成交额，以元为单位 */
	private String turnover;
	/*** 周k线图 */
	private String weekurl;

}
