package com.zmm.util;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * @ClassName: MailUtils
 * @Description: 发邮件工具类�?
 * @author c-kx@outlook.com
 * @date 2015�?12�?20�? 下午6:24:42
 * 
 */
public class MailUtils {
	/**
	 * @Description: 得到session
	 * @param host
	 * @param username
	 * @param password
	 * @return
	 */
	public static Session createSession(String host, final String username, final String password) {
		Properties prop = new Properties();
		prop.setProperty("mail.host", host);// 指定主机
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.auth", "true");// 指定验证为true

		// 创建验证�?
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		};

		// 获取session对象
		return Session.getInstance(prop, auth);
	}

	/**
	 * @Description:发�?�指定的邮件
	 * @param session
	 * @param mail
	 * @throws MessagingException
	 * @throws IOException
	 */

	public static void send(Session session, final Mail mail) throws MessagingException, IOException {

		MimeMessage msg = new MimeMessage(session);// 创建邮件对象
		msg.setFrom(new InternetAddress(mail.getFrom()));// 设置发件�?
		msg.addRecipients(RecipientType.TO, mail.getToAddress());// 设置收件�?

		// 设置抄�??
		String cc = mail.getCcAddress();
		if (!cc.isEmpty()) {
			msg.addRecipients(RecipientType.CC, cc);
		}

		// 设置暗�??
		String bcc = mail.getBccAddress();
		if (!bcc.isEmpty()) {
			msg.addRecipients(RecipientType.BCC, bcc);
		}

		msg.setSubject(mail.getSubject());// 设置主题

		MimeMultipart parts = new MimeMultipart();// 创建部件集对�?

		MimeBodyPart part = new MimeBodyPart();// 创建�?个部�?
		part.setContent(mail.getContent(), "text/html;charset=utf-8");// 设置邮件文本内容
		parts.addBodyPart(part);// 把部件添加到部件集中

		///////////////////////////////////////////

		// 添加附件
		List<AttachBean> attachBeanList = mail.getAttachs();// 获取�?有附�?
		if (attachBeanList != null) {
			for (AttachBean attach : attachBeanList) {
				MimeBodyPart attachPart = new MimeBodyPart();// 创建�?个部�?
				attachPart.attachFile(attach.getFile());// 设置附件文件
				attachPart.setFileName(MimeUtility.encodeText(attach.getFileName()));// 设置附件文件�?
				String cid = attach.getCid();
				if (cid != null) {
					attachPart.setContentID(cid);
				}
				parts.addBodyPart(attachPart);
			}
		}

		msg.setContent(parts);// 给邮件设置内�?
		Transport.send(msg);// 发邮�?
	}
}
