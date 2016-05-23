/*** */
package com.socool.site.biz.testinfo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socool.site.biz.BaseBiz;
import com.socool.site.biz.testinfo.IInterviewTestBiz;
import com.socool.site.bo.testinfo.TestInfoBo;
import com.socool.site.condition.TestInfoCondition;
import com.socool.site.dao.testinfo.ITestInfoDao;
import com.socool.site.entry.testinfo.TestInfoEntry;

/**
 * @author mr.lwp
 * @see 2016年5月21日
 */
@Service
public class InterviewTestBizImpl extends BaseBiz implements IInterviewTestBiz {
	@Autowired
	private ITestInfoDao iTestInfoDao;

	@Override
	public List<TestInfoBo> queryInterview(final TestInfoCondition condition) {
		final List<TestInfoEntry> entryList = iTestInfoDao.queryTestInfo(condition);
		final List<TestInfoBo> list = mapList(entryList, TestInfoBo.class);
		for (final TestInfoBo testInfoBo : list) {
			final String[] options = testInfoBo.getTestOptions().split(";");
			testInfoBo.setOptionsArr(options);
		}
		return list;
	}

	@Override
	public int queryInterviewCount(final TestInfoCondition condition) {
		final int count = iTestInfoDao.queryTestInfoCount(condition);
		return count;
	}
}
