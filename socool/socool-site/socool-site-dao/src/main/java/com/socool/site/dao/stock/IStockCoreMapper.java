/****/
package com.socool.site.dao.stock;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.socool.site.bo.stock.StockPoolBo;
import com.socool.site.condition.stock.SockCondition;
import com.socool.site.entry.stock.StockHqEntry;
import com.socool.site.entry.stock.StockPoolEntry;

/**
 * @author liuwp
 * @date 2016年11月24日
 */
@Repository
public interface IStockCoreMapper {
	/**
	 * @return
	 */
	@Select("SELECT * FROM qdm20999941_db.stock_pool")
	List<StockPoolEntry> getStockPoolList();

	/**
	 * @param code
	 * @return
	 */
	List<StockHqEntry> getStockHqList(final SockCondition condition);

	/**
	 * @return
	 */
	int getStockHqCount(final SockCondition condition);

	/**
	 * @param list
	 * @return
	 */
	int insertStockHq(@Param("list") final List<StockHqEntry> list);

	/**
	 * @param list
	 * @return
	 */
	int insertStockPool(final List<StockPoolBo> list);
}
