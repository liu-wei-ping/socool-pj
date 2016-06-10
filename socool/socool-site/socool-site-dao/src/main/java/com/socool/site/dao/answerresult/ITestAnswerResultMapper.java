/*** */
package com.socool.site.dao.answerresult;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.socool.site.entry.answerresult.TestAnswerResultEntry;

/**
 * @author mr.lwp
 * @see 2016年6月5日
 */
@Repository
public interface ITestAnswerResultMapper {
	/**
	 * @param list
	 * @return
	 */
	int insertTestAnswerResult(@Param("list") final List<TestAnswerResultEntry> list);

	/**
	 * @param reqMap
	 * @return
	 */
	List<Map<String, Object>> queryTestAnswerResult(final Map<String, Object> reqMap);

}
