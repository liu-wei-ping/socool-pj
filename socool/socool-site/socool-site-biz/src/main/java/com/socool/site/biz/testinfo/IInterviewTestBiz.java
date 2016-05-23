/*** */
package com.socool.site.biz.testinfo;

import java.util.List;

import com.socool.site.bo.testinfo.TestInfoBo;
import com.socool.site.condition.TestInfoCondition;

/**
 * @author mr.lwp
 * @see 2016年5月21日
 */
public interface IInterviewTestBiz {
	/**
	 * @return
	 */
	List<TestInfoBo> queryInterview(TestInfoCondition condition);

	/**
	 * @param condition
	 * @return
	 */
	int queryInterviewCount(TestInfoCondition condition);

}
