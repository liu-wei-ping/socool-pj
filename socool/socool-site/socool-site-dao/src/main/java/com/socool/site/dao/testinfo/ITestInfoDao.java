/*** */
package com.socool.site.dao.testinfo;

import java.util.List;

import com.socool.site.condition.TestInfoCondition;
import com.socool.site.entry.testinfo.TestInfoEntry;

/**
 * @author mr.lwp
 * @see 2016年5月21日
 */
public interface ITestInfoDao {
	/**
	 * @param condition
	 * @return
	 */
	List<TestInfoEntry> queryTestInfo(TestInfoCondition condition);

	/**
	 * @param condition
	 * @return
	 */
	int queryTestInfoCount(TestInfoCondition condition);
}
