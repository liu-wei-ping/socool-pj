package com.socool.site.dao.answerresult.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public List<TestAnswerResultEntry> queryTestAnswerResult(final Long uid,
			final String categoryType) {
		final Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("user_id", uid);
		reqMap.put("category_type", categoryType);

		final List<TestAnswerResultEntry> mapList = mapper
				.queryTestAnswerResult(reqMap);
		return mapList;
	}
}
