/****/
package com.socool.site.biz.utils;

/**
 * @author lwp
 * @date 2016年5月11日
 */
public class Constants {
	/**
	 * 验证码KEY
	 */
	public final static String LOGIN_CODE = "login_code";
	/*** 登录次数过多间隔时间 单位秒 例如：10分钟 10*60 */
	public final static int LOGIN_FAIL_TIME = 10 * 60;
	/*** 登录失败次数阀值 */
	public final static String LOGIN_MAX_FAIL = "3";
	/*** 登录失败次数阀值 KEY */
	public final static String LOGIN_MAX_FAIL_KEY = "SOCOOL_LOGIN_FAIL_MAX";
	/**
	 * 登陆页面
	 */
	public final static String LOGIN_URL = "/login/login.html";
	/*** 最大展示数据(JAVA) */
	public final static int MAX_JAVA_PAGE = 3;

	/**
	 * 登录成功凭证 KEY
	 */
	public static final String SESSION_USER = "socool_user";
	/**
	 * 成功标识 KEY
	 */
	public final static String SUCCESS = "success";
	public final static String TYPE_ANDROID = "android";
	public final static String TYPE_IOS = "ios";
	public final static String TYPE_JAVA = "java";
	public final static String TYPE_PHP = "php";
}
