/*** */
package com.socool.site.dao.testinfo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.socool.site.condition.TestInfoCondition;
import com.socool.site.dao.testinfo.ITestInfoDao;
import com.socool.site.dao.testinfo.ITestInfoMapper;
import com.socool.site.entry.testinfo.TestInfoEntry;

/**
 * @author mr.lwp
 * @see 2016年5月21日
 */
@Repository
class TestInfoDaoImpl implements ITestInfoDao {

	@Autowired
	private ITestInfoMapper mapper;

	@Override
	public List<TestInfoEntry> queryTestInfo(final TestInfoCondition condition) {
		return mapper.getTestInfo(condition);
	}

	@Override
	public int queryTestInfoCount(final TestInfoCondition condition) {
		return mapper.getTestInfoCount(condition);
	}

}
