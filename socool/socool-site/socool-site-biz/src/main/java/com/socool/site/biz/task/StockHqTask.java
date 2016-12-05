package com.socool.site.biz.task;

import java.sql.Timestamp;

import lombok.extern.slf4j.Slf4j;

import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.socool.site.biz.stock.IStockCoreBiz;

@Slf4j
public class StockHqTask {
	@Autowired
	private IStockCoreBiz iStockCoreBiz;

	public final void execute() throws JobExecutionException {
		System.out.println("Start......");
		toDoTask();
	}

	private void toDoTask() {
		try {
			final Timestamp nowTime = new Timestamp(System.currentTimeMillis());
			log.info(nowTime + "--开始加载行情时间--");
			final boolean f = iStockCoreBiz.updateStockHqInfo(null);
			final Timestamp endTime = new Timestamp(System.currentTimeMillis());
			log.info(endTime + "--结束加载行情时间--");
		} catch (final Exception e) {
			log.error("加载行情错误!");
		}
	}

}
