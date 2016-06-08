/*** */
package com.socool.site.biz.answerresult.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socool.site.biz.BaseBiz;
import com.socool.site.biz.answerresult.ITestAnswerResultBiz;
import com.socool.site.bo.answerresult.TestAnswerResultBo;
import com.socool.site.dao.answerresult.ITestAnswerResultDao;
import com.socool.site.entry.answerresult.TestAnswerResultEntry;

/**
 * @author mr.lwp
 * @see 2016年6月5日
 */
@Service
public class TestAnswerResultBizImpl extends BaseBiz implements
		ITestAnswerResultBiz {
	@Autowired
	private ITestAnswerResultDao dao;

	@Override
	public List<TestAnswerResultBo> queryTestAnswerResult(final Long uid,
			final String categoryType) {
		final List<TestAnswerResultEntry> entryList = dao
				.queryTestAnswerResult(uid, categoryType);
		final List<TestAnswerResultBo> list = mapList(entryList,
				TestAnswerResultBo.class);
		return list;
	}

	@Override
	public boolean saveTestAnswerResult(final List<TestAnswerResultBo> list) {
		final List<TestAnswerResultEntry> entryList = mapList(list,
				TestAnswerResultEntry.class);
		return dao.insertTestAnswerResult(entryList) > 0;
	}

}
