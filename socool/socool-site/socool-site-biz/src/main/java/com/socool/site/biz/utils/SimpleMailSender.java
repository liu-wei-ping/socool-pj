/****/
package com.socool.site.biz.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.socool.site.biz.email.MailAuthenticator;
import com.socool.site.bo.email.SimpleMail;

/**
 * @author liuwp
 * @date 2016年8月12日
 */
public class SimpleMailSender {
	/**
	 * 邮件服务器登录验证
	 */
	private transient MailAuthenticator authenticator;

	private BodyPart bodypart = null;
	private MimeMessage mimeMessage = null;

	/*** */
	private Multipart multipart = null;
	/**
	 * 发送邮件的props文件
	 */
	private final transient Properties props = System.getProperties();

	/**
	 * 邮箱session
	 */
	private transient Session session;

	/**
	 * 初始化邮件发送器
	 *
	 * @param username
	 *            发送邮件的用户名(地址)，并以此解析SMTP服务器地址
	 * @param password
	 *            发送邮件的密码
	 */
	public SimpleMailSender(final String username, final String password) {
		// 通过邮箱地址解析出smtp服务器，对大多数邮箱都管用
		final String smtpHostName = "smtp." + username.split("@")[1];
		init(username, password, smtpHostName);
	}

	/**
	 * 初始化邮件发送器
	 *
	 * @param smtpHostName
	 *            SMTP邮件服务器地址
	 * @param username
	 *            发送邮件的用户名(地址)
	 * @param password
	 *            发送邮件的密码
	 */
	public SimpleMailSender(final String smtpHostName, final String username, final String password) {
		init(username, password, smtpHostName);
	}

	/**
	 * 群发邮件
	 *
	 * @param recipients
	 *            收件人们
	 * @param mail
	 *            邮件对象
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void send(final List<String> recipients, final SimpleMail mail) throws AddressException, MessagingException {
		send(recipients, mail.getSubject(), mail.getContent());
	}

	/**
	 * 群发邮件
	 *
	 * @param recipients
	 *            收件人们
	 * @param subject
	 *            主题
	 * @param content
	 *            内容
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void send(final List<String> recipients, final String subject, final Object content)
			throws AddressException, MessagingException {
		// 创建mime类型邮件
		// final MimeMessage message = new MimeMessage(session);
		// 设置发信人
		mimeMessage.setFrom(new InternetAddress(authenticator.getUsername()));
		// 设置收件人们
		final int num = recipients.size();
		mimeMessage.setSentDate(new Date());
		final InternetAddress[] addresses = new InternetAddress[num];
		for (int i = 0; i < num; i++) {
			addresses[i] = new InternetAddress(recipients.get(i));
		}
		mimeMessage.setRecipients(RecipientType.TO, addresses);
		// 设置主题
		mimeMessage.setSubject(subject);
		// 设置邮件内容
		mimeMessage.setContent(content.toString(), "text/html;charset=utf-8");
		// 发送
		Transport.send(mimeMessage);
	}

	/**
	 * 发送邮件
	 *
	 * @param recipient
	 *            收件人邮箱地址
	 * @param mail
	 *            邮件对象
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void send(final String recipient, final SimpleMail mail) throws AddressException, MessagingException {
		send(recipient, mail.getSubject(), mail.getContent());
	}

	/**
	 * 发送邮件
	 *
	 * @param recipient
	 *            收件人邮箱地址
	 * @param subject
	 *            邮件主题
	 * @param content
	 *            邮件内容
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void send(final String recipient, final String subject, final Object content)
			throws AddressException, MessagingException {
		// 创建mime类型邮件
		final MimeMessage message = new MimeMessage(session);
		// 设置发信人
		message.setFrom(new InternetAddress(authenticator.getUsername()));
		// 设置收件人
		message.setRecipient(RecipientType.TO, new InternetAddress(recipient));

		// 设置主题
		message.setSubject(subject);
		// 设置邮件内容
		message.setContent(content.toString(), "text/html;charset=utf-8");
		// 发送
		Transport.send(message);
	}

	public void setMultipart(final String file) throws MessagingException, IOException {
		if (multipart == null) {
			multipart = new MimeMultipart();
		}
		multipart.addBodyPart(writeFiles(file));
		mimeMessage.setContent(multipart);
	}

	/**
	 * 读取附件
	 *
	 * @param filePath
	 * @return
	 * @throws IOException
	 * @throws MessagingException
	 */
	public BodyPart writeFiles(final String filePath) throws IOException, MessagingException {
		final File file = new File(filePath);
		if (!file.exists()) {
			throw new IOException("文件不存在!请确定文件路径是否正确");
		}
		bodypart = new MimeBodyPart();
		final DataSource dataSource = new FileDataSource(file);
		bodypart.setDataHandler(new DataHandler(dataSource));
		// 文件名要加入编码，不然出现乱码
		bodypart.setFileName(MimeUtility.encodeText(file.getName()));
		return bodypart;
	}

	/**
	 * 初始化
	 *
	 * @param username
	 *            发送邮件的用户名(地址)
	 * @param password
	 *            密码
	 * @param smtpHostName
	 *            SMTP主机地址
	 */
	private void init(final String username, final String password, final String smtpHostName) {
		if (smtpHostName.indexOf("qq") != -1) {
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.socketFactory.port", "465");
		}
		// 初始化props
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", smtpHostName);
		// 验证
		authenticator = new MailAuthenticator(username, password);
		// 创建session
		session = Session.getInstance(props, authenticator);
		if (mimeMessage == null) {
			mimeMessage = new MimeMessage(session);
		}

	}

}
