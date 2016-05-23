/****/
package com.socool.site.bo.userinfo;

import lombok.Data;

/**
 * @author liuwp
 * @date 2016年5月11日
 */
@Data
public class UserInfo {
	private String code;
	private String password;
	private long uid;
	private String username;
}
