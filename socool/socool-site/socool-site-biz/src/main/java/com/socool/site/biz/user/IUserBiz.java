/*** */
package com.socool.site.biz.user;

import com.socool.site.bo.userinfo.UserInfo;

/**
 * @author mr.lwp
 * @see 2016年12月10日
 */
public interface IUserBiz {
	/**
	 * @param userInfo
	 * @return
	 */
	String LoginValidate(UserInfo userInfo);
}
