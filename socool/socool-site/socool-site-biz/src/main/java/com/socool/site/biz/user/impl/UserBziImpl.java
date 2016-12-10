/*** */
package com.socool.site.biz.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socool.site.biz.user.IUserBiz;
import com.socool.site.biz.utils.MD5Util;
import com.socool.site.biz.utils.RSAUtils;
import com.socool.site.bo.userinfo.UserInfo;
import com.socool.site.dao.user.IUserDao;

/**
 * @author mr.lwp
 * @see 2016年12月10日
 */
@Service
public class UserBziImpl implements IUserBiz {

	public static void main(final String[] args) {
		final String pwd = MD5Util.string2MD5("lwp123");
		System.out.println(pwd);
	}

	@Autowired
	private IUserDao iUserDao;

	@Override
	public String LoginValidate(final UserInfo userInfo) {
		if (userInfo != null) {
			final String pwd = RSAUtils.decryptStringByJs(userInfo.getPassword());
			final String pwdS = MD5Util.string2MD5(pwd);
			userInfo.setPassword(pwdS);
			return iUserDao.LoginValidate(userInfo);
		}
		return null;
	}
}
