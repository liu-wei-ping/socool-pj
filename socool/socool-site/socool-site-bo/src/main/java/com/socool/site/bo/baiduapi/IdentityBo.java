/****/
package com.socool.site.bo.baiduapi;

import java.io.Serializable;

import lombok.Data;

/**
 * @author liuwp
 * @date 2016年7月4日
 */
@Data
public class IdentityBo implements Serializable {

	/*** */
	private static final long serialVersionUID = 1L;
	private String address;
	private String birthday;
	private String sex;
	private String sexStr;
}
