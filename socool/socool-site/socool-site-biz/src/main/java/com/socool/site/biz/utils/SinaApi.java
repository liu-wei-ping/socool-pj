/****/
package com.socool.site.biz.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;

import com.socool.site.entry.stock.StockHqEntry;

/**
 * @author liuwp
 * @date 2016年11月24日
 */
@Slf4j
public class SinaApi {

	public static String getStockHqInfo(final String params) {
		String url = "http://hq.sinajs.cn/list";
		url = url.concat("=").concat(params);
		final String result = HttpSendUtil.httpGet(url);
		return result;
	}

	public static void parseStockInfo(final StockHqEntry entry,
			final String[] info) {
		if (info.length > 0 && entry != null) {
			for (int i = 0; i < info.length; i++) {
				switch (i) {
				case 0:
					entry.setName(info[i]);
					break;
				case 1:
					entry.setOpenningprice(new BigDecimal(info[i]));
					break;
				case 2:
					entry.setClosingprice(new BigDecimal(info[i]));
					break;
				case 3:
					entry.setCurrentprice(new BigDecimal(info[i]));
					break;
				case 4:
					entry.setHprice(new BigDecimal(info[i]));
					break;
				case 5:
					entry.setLprice(new BigDecimal(info[i]));
					break;
				case 6:
					entry.setCompetitiveprice(new BigDecimal(info[i]));
					break;
				case 7:
					entry.setAuctionprice(new BigDecimal(info[i]));
					break;
				case 8:
					entry.setTotalnumber(Integer.valueOf(info[i]));
					break;
				case 9:
					entry.setTurnover(new BigDecimal(info[i]));
					break;
				case 30:
					entry.setDate(info[i]);
					break;
				case 31:
					entry.setTime(info[i]);
					break;
				}
			}
		}
	}

	public static List<StockHqEntry> parseStockResult(final String result) {
		final List<StockHqEntry> list = new ArrayList<StockHqEntry>();
		final String[] listStr = result.split(";");
		for (int i = 0; i < listStr.length; i++) {
			try {
				final String[] objStr = listStr[i].split("=");
				final String leftStr = objStr[0];
				final String code = leftStr
						.substring(leftStr.lastIndexOf("_") + 1);
				final String rightStr = objStr[1];
				if (!StringUtils.isEmpty(rightStr)) {
					final StockHqEntry entry = new StockHqEntry();
					entry.setCode(code);
					final String[] info = rightStr.replaceAll("\"", "").split(
							",");
					parseStockInfo(entry, info);
					list.add(entry);
				}
			} catch (final Exception e) {
				log.error(listStr[i].concat("股票行情解析错误"));
				continue;
			}

		}
		return list;
	}
}
