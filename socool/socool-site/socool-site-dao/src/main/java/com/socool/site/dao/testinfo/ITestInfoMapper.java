/*** */
package com.socool.site.dao.testinfo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.socool.site.condition.TestInfoCondition;
import com.socool.site.entry.testinfo.TestInfoEntry;

/**
 * @author mr.lwp
 * @see 2016年5月21日
 */
@Repository
public interface ITestInfoMapper {
	/**
	 * @param condition
	 * @return
	 */
	List<TestInfoEntry> getTestInfo(TestInfoCondition condition);

	/**
	 * @param condition
	 * @return
	 */
	int getTestInfoCount(TestInfoCondition condition);
}
