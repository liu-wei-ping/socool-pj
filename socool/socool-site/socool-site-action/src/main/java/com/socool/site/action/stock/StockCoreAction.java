/****/
package com.socool.site.action.stock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.socool.site.biz.stock.IStockCoreBiz;
import com.socool.site.bo.baiduapi.StockInfoBo;
import com.socool.site.bo.stock.StockPoolBo;
import com.socool.site.condition.PageCondition;
import com.socool.site.condition.stock.SockCondition;

/**
 * @author liuwp
 * @date 2016年11月24日
 */
@Controller
@RequestMapping("stock")
public class StockCoreAction {
	@Autowired
	private IStockCoreBiz iStockCoreBiz;

	@ResponseBody
	@RequestMapping(value = "/getStockHqInfo.shtml", method = RequestMethod.POST)
	public Map<String, Object> getStockHqInfo(final SockCondition condition,
			final PageCondition page) {
		condition.setPageCondition(new PageCondition(page));
		String startDate = condition.getStartDate();
		String endDate = condition.getEndDate();
		if (StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)) {
			final Calendar cal = Calendar.getInstance();
			final int year = cal.get(Calendar.YEAR);
			final int month = cal.get(Calendar.MONTH) + 1;
			final int day = cal.get(Calendar.DATE);
			final String dayStr = day < 10 ? "0" + String.valueOf(day) : String
					.valueOf(day);
			if (StringUtils.isBlank(startDate)) {
				startDate = year + "-" + month + "-" + dayStr;
			}
			if (StringUtils.isBlank(endDate)) {
				endDate = year + "-" + month + "-" + dayStr;
			}
		}
		condition.setStartDate(startDate);
		condition.setEndDate(endDate);
		final Map<String, Object> reqMap = new HashMap<String, Object>();
		final int total = iStockCoreBiz.getStockHqCount(condition);
		List<StockInfoBo> list = new ArrayList<StockInfoBo>();
		if (total > 0) {
			list = iStockCoreBiz.getStockHqInfo(condition);
		}
		reqMap.put("total", total);
		reqMap.put("rows", list);
		return reqMap;
	}

	@RequestMapping("/index.html")
	public ModelAndView index() {
		final ModelAndView model = new ModelAndView();
		model.setViewName("stock/stock_index");
		return model;
	}

	@RequestMapping(value = "/loadNewStock.shtml", method = RequestMethod.POST)
	public Map<String, Object> loadNewStock(final StockPoolBo stockCode) {
		final Map<String, Object> reqMap = new HashMap<String, Object>();
		final List<StockPoolBo> list = new ArrayList<StockPoolBo>();
		list.add(stockCode);
		final boolean f = iStockCoreBiz.insertStockPool(list);
		reqMap.put("result", f ? "Y" : "N");
		return reqMap;
	}

	@ResponseBody
	@RequestMapping("/updatestockHq.shtml")
	public Map<String, Object> updateStockHqInfo(final String stockCode) {
		final Map<String, Object> reqMap = new HashMap<String, Object>();
		final boolean f = iStockCoreBiz.updateStockHqInfo(stockCode);
		reqMap.put("result", f ? "Y" : "N");
		return reqMap;
	}
}
