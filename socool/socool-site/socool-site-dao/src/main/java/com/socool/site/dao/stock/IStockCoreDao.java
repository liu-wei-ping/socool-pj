/****/
package com.socool.site.dao.stock;

import java.util.List;

import com.socool.site.bo.stock.StockPoolBo;
import com.socool.site.condition.stock.SockCondition;
import com.socool.site.entry.stock.StockHqEntry;
import com.socool.site.entry.stock.StockPoolEntry;

/**
 * @author liuwp
 * @date 2016年11月24日
 */
public interface IStockCoreDao {
	/**
	 * @return
	 */
	List<StockPoolEntry> getStockPoolList();

	/**
	 * @param stock
	 * @return
	 */
	List<StockHqEntry> getStockHqList(final SockCondition condition);

	/**
	 * @param stock
	 * @return
	 */
	int getStockHqCount(final SockCondition condition);

	/**
	 * @param list
	 * @return
	 */
	boolean insertStockHq(final List<StockHqEntry> list);

	/**
	 * @param list
	 * @return
	 */
	boolean insertStockPool(final List<StockPoolBo> list);
}
