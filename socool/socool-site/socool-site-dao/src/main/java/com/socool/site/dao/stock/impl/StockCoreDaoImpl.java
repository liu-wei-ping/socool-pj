/****/
package com.socool.site.dao.stock.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.socool.site.bo.stock.StockPoolBo;
import com.socool.site.condition.stock.SockCondition;
import com.socool.site.dao.stock.IStockCoreDao;
import com.socool.site.dao.stock.IStockCoreMapper;
import com.socool.site.entry.stock.StockHqEntry;
import com.socool.site.entry.stock.StockPoolEntry;

/**
 * @author liuwp
 * @date 2016年11月24日
 */
@Repository
class StockCoreDaoImpl implements IStockCoreDao {
	@Autowired
	private IStockCoreMapper mapper;

	@Override
	public int getStockHqCount(final SockCondition condition) {
		return mapper.getStockHqCount(condition);
	}

	@Override
	public List<StockHqEntry> getStockHqList(final SockCondition condition) {
		return mapper.getStockHqList(condition);
	}

	@Override
	public List<StockPoolEntry> getStockPoolList() {
		return mapper.getStockPoolList();
	}

	@Override
	public boolean insertStockHq(final List<StockHqEntry> list) {
		final Integer i = mapper.insertStockHq(list);
		return i > 0;
	}

	@Override
	public boolean insertStockPool(final List<StockPoolBo> list) {
		final int now = mapper.insertStockPool(list);
		return now > 0;
	}

}
