package com.socool.site.dao.answerresult.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.socool.site.dao.answerresult.ITestAnswerResultDao;
import com.socool.site.dao.answerresult.ITestAnswerResultMapper;
import com.socool.site.entry.answerresult.TestAnswerResultEntry;

/**
 * @author mr.lwp
 * @see 2016年6月5日
 */
@Repository
class TestAnswerResultDaoImpl implements ITestAnswerResultDao {
	@Autowired
	private ITestAnswerResultMapper mapper;

	@Override
	public int insertTestAnswerResult(final List<TestAnswerResultEntry> list) {
		return mapper.insertTestAnswerResult(list);
	}

}
