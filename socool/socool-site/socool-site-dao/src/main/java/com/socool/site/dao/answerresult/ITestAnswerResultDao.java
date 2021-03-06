package com.socool.site.dao.answerresult;

import java.util.List;
import java.util.Map;

import com.socool.site.entry.answerresult.TestAnswerResultEntry;

/**
 * @author mr.lwp
 * @see 2016年6月5日
 */
public interface ITestAnswerResultDao {

	/**
	 * @param list
	 * @return
	 */
	int insertTestAnswerResult(final List<TestAnswerResultEntry> list);

	/**
	 * @param uid
	 * @param categoryType
	 * @return
	 */
	List<Map<String, Object>> queryTestAnswerResult(final Long uid, final String categoryType);
}
