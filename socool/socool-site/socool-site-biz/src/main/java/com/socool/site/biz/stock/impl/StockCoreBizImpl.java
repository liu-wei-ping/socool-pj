/****/
package com.socool.site.biz.stock.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socool.site.biz.BaseBiz;
import com.socool.site.biz.stock.IStockCoreBiz;
import com.socool.site.biz.utils.SinaApi;
import com.socool.site.bo.baiduapi.StockInfoBo;
import com.socool.site.bo.stock.StockPoolBo;
import com.socool.site.condition.stock.SockCondition;
import com.socool.site.dao.stock.IStockCoreDao;
import com.socool.site.entry.stock.StockHqEntry;
import com.socool.site.entry.stock.StockPoolEntry;

/**
 * @author liuwp
 * @date 2016年11月24日
 */
@Service
public class StockCoreBizImpl extends BaseBiz implements IStockCoreBiz {

	@Autowired
	private IStockCoreDao iStockCoreDao;

	@Override
	public int getStockHqCount(final SockCondition condition) {
		return iStockCoreDao.getStockHqCount(condition);
	}

	@Override
	public List<StockInfoBo> getStockHqInfo(final SockCondition condition) {
		final List<StockHqEntry> entryList = iStockCoreDao
				.getStockHqList(condition);
		final List<StockInfoBo> list = mapList(entryList, StockInfoBo.class);
		return list;

	}

	@Override
	public boolean insertStockPool(final List<StockPoolBo> list) {
		return iStockCoreDao.insertStockPool(list);
	}

	@Override
	public boolean updateStockHqInfo(final String stockCode) {
		final List<StockPoolEntry> list = iStockCoreDao.getStockPoolList();
		final String params = getStockParams(list);
		try {
			final String result = SinaApi.getStockHqInfo(params);
			final List<StockHqEntry> hqList = SinaApi.parseStockResult(result);
			final boolean f = iStockCoreDao.insertStockHq(hqList);
			return f;
		} catch (final Exception e) {
			return false;
		}

	}

	private String getStockParams(final List<StockPoolEntry> list) {
		String params = "";
		for (final StockPoolEntry entry : list) {
			final String code = entry.getStockCode();
			final String excode = entry.getExcode().toLowerCase();
			params = params.concat(excode).concat(code).concat(",");

		}
		return params;
	}

}
