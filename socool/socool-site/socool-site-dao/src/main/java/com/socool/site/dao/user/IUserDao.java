/*** */
package com.socool.site.dao.user;

import com.socool.site.bo.userinfo.UserInfo;

/**
 * @author mr.lwp
 * @see 2016年12月10日
 */
public interface IUserDao {
	/**
	 * @param userInfo
	 * @return
	 */
	String LoginValidate(UserInfo userInfo);
}
