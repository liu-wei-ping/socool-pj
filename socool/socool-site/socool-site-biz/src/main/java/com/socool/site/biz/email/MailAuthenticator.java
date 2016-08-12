/****/
package com.socool.site.biz.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @author liuwp
 * @date 2016年8月12日
 */
public class MailAuthenticator extends Authenticator {
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 用户名（登录邮箱）
	 */
	private String username;

	/**
	 * 初始化邮箱和密码
	 *
	 * @param username
	 *            邮箱
	 * @param password
	 *            密码
	 */
	public MailAuthenticator(final String username, final String password) {
		this.username = username;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}
}
