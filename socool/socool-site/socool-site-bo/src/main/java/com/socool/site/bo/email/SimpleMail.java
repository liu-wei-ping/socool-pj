/****/
package com.socool.site.bo.email;

/**
 * @author liuwp
 * @date 2016年8月12日
 */
public class SimpleMail {
	private String content;

	private String subject;

	public String getContent() {
		return content;
	}

	public String getSubject() {
		return subject;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}
}
