/****/
package com.socool.site.action.java;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.socool.site.biz.email.MailSenderFactory;
import com.socool.site.biz.utils.SimpleMailSender;
import com.socool.site.bo.email.SendMailBo;
import com.socool.site.bo.email.SimpleMail;

/**
 * @author liuwp
 * @date 2016年8月12日
 */
@Controller
@RequestMapping("mail")
public class SendEmailAction {

	public static void main(final String[] args) {
		final SendEmailAction s = new SendEmailAction();
		final SendMailBo b = new SendMailBo();
		final String f = "323212891@qq.com";
		b.setFromMail(f);
		b.setToMail(Arrays.asList("liu_weipinglove@163.com"));
		b.setContent("你好！测试邮箱");
		s.send(b);
	}

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
			final InternetAddress address = new InternetAddress(mail_from, "刘伟平love");
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

	public String send(final SendMailBo mailInfo) {
		String result = "N";
		// sendMail("liu_weipinglove@163.com", "323212891@qq.com");
		try {
			final SimpleMailSender sender = MailSenderFactory.getSender();
			final List<String> recipients = mailInfo.getToMail();
			final SimpleMail mail = new SimpleMail();
			mail.setContent(mailInfo.getContent());
			mail.setSubject(mailInfo.getSubject());
			mail.setFileUrl(mailInfo.getFileUrl());
			sender.send(recipients, mail);
			// for (final String recipient : recipients) {
			// sender.send(recipient, mailInfo.getSubject(),
			// mailInfo.getContent());
			// // sender.send(recipients, subject, content);
			// }
			result = "Y";
		} catch (final IOException | MessagingException e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "/sendMail.shtml", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> sendMail(final SendMailBo mailInfo) {
		final Map<String, Object> map = new HashMap<String, Object>();
		final String result = send(mailInfo);
		map.put("result", result);
		return map;

	}
}
