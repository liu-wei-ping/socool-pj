package com.socool.site.dao.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.socool.site.bo.userinfo.UserInfo;
import com.socool.site.dao.user.IUserDao;
import com.socool.site.dao.user.IUserMapper;

@Repository
class UserDaoImpl implements IUserDao {
	@Autowired
	private IUserMapper mapper;

	@Override
	public String LoginValidate(final UserInfo userInfo) {
		final String username = mapper.getUserName(userInfo.getUsername(), userInfo.getPassword());
		return username;
	}

}
