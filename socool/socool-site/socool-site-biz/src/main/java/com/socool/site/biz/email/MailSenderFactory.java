/****/
package com.socool.site.biz.email;

import java.io.IOException;
import java.util.Properties;

import com.socool.site.biz.utils.PropertiesUtil;
import com.socool.site.biz.utils.SimpleMailSender;

/**
 * @author liuwp
 * @date 2016年8月12日
 */
public class MailSenderFactory {
	/**
	 * 服务邮箱
	 */
	private static SimpleMailSender serviceSms = null;

	/**
	 * 获取邮箱
	 *
	 * @param type
	 *            邮箱类型
	 * @return 符合类型的邮箱
	 * @throws IOException
	 */
	public static SimpleMailSender getSender() throws IOException {
		if (initUser() == null) {
			serviceSms = new SimpleMailSender("liu_weipinglove@163.com", "lwplzg1989");
		}
		return serviceSms;
	}

	private static SimpleMailSender initUser() throws IOException {
		final Properties p = PropertiesUtil.getInstance("config/mail-user.properties");
		if (null == p) {
			return null;
		}
		serviceSms = new SimpleMailSender(p.getProperty("user.name"), p.getProperty("user.password"));
		System.out.println(serviceSms);
		return serviceSms;
	}
}
