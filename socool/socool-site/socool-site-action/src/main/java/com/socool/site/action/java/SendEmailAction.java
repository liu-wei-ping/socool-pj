/****/
package com.socool.site.action.java;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.socool.site.biz.email.MailSenderFactory;
import com.socool.site.biz.utils.SimpleMailSender;
import com.socool.site.bo.email.SendMailBo;

/**
 * @author liuwp
 * @date 2016年8月12日
 */
public class SendEmailAction {

	public static void sendMail(final String mail_from, final String mail_to) {
		final String host = "smtp.163.com";
		// final String host = "smtp.qq.com";
		try {
			final Properties props = new Properties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "false");
			final Session mailSession = Session.getInstance(props);

			// 设置session,和邮件服务器进行通讯。
			mailSession.setDebug(true);
			final MimeMessage message = new MimeMessage(mailSession);
			message.setSubject("This is the Subject Line!"); // 设置邮件主题
			message.setText("你好！"); // 设置邮件正文
			// 发送 HTML 消息, 可以插入html标签
			message.setContent("<h1>This is actual message</h1>", "text/html");
			message.setHeader("title", "刘伟平发送的第一封邮箱"); // 设置邮件标题

			message.setSentDate(new Date()); // 设置邮件发送日期
			final InternetAddress address = new InternetAddress(mail_from,
					"刘伟平love");
			message.setFrom(address); // 设置邮件发送者的地址
			final InternetAddress toAddress = new InternetAddress(mail_to); // 设置邮件接收方的地址
			message.addRecipient(Message.RecipientType.TO, toAddress);
			Transport transport = null;
			transport = mailSession.getTransport("smtp");
			message.saveChanges();
			transport.connect(host, "13012881773@163.com", "lwplzg1989");
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

			System.out.println("send success!");
		} catch (final Exception ex) {
			ex.printStackTrace();
		}
	}

	@RequestMapping(value = "/sendMail.shtml", method = RequestMethod.POST)
	public Map<String, Object> sendMail(@RequestBody final SendMailBo mailInfo) {
		final Map<String, Object> map = new HashMap<String, Object>();
		send(mailInfo);
		return map;

	}

	private void send(final SendMailBo mailInfo) {
		// sendMail("liu_weipinglove@163.com", "323212891@qq.com");
		try {
			final SimpleMailSender sender = MailSenderFactory.getSender();
			// final List<String> recipients = new ArrayList<String>();
			// for (int i = 0; i < 10; i++) {
			// recipients.add("323212891@qq.com");
			// }
			final List<String> recipients = mailInfo.getToMail();
			for (final String recipient : recipients) {
				sender.send(recipient, mailInfo.getSubject(),
						mailInfo.getContent());
				// sender.send(recipients, subject, content);
			}
		} catch (final IOException e) {
			e.printStackTrace();
		} catch (final AddressException e) {
			e.printStackTrace();
		} catch (final MessagingException e) {
			e.printStackTrace();
		}
	}
}
