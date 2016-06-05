/*** */
package com.socool.site.biz.answerresult;

import java.util.List;

import com.socool.site.bo.answerresult.TestAnswerResultBo;

/**
 * @author mr.lwp
 * @see 2016年6月5日
 */
public interface ITestAnswerResultBiz {
	/**
	 * @param list
	 * @return
	 */
	boolean saveTestAnswerResult(List<TestAnswerResultBo> list);
}
