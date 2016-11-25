/****/
package com.socool.site.biz.stock;

import java.util.List;

import com.socool.site.bo.baiduapi.StockInfoBo;
import com.socool.site.bo.stock.StockPoolBo;
import com.socool.site.condition.stock.SockCondition;

/**
 * @author liuwp
 * @date 2016年11月24日
 */
public interface IStockCoreBiz {
	/**
	 * @param stock
	 * @return
	 */
	List<StockInfoBo> getStockHqInfo(final SockCondition condition);

	/**
	 * @param stock
	 * @return
	 */
	int getStockHqCount(final SockCondition condition);

	/**
	 * @param stockCode
	 * @return
	 */
	boolean updateStockHqInfo(final String stockCode);

	/**
	 * @param list
	 * @return
	 */
	boolean insertStockPool(final List<StockPoolBo> list);
}
