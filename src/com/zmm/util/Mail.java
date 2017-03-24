package com.zmm.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 表示邮件类，你需要设置：账户名和密码、收件人、抄�?(可�??)、暗�?(可�??)、主题�?�内容，以及附件(可�??)
 * 
 * 在创建了Mail对象之后
 * 可以调用它的setSubject()、setContent()，设置主题和正文
 * 也可以调用setFrom()和�??addToAddress()，设置发件人，和添加收件人�??
 * 也可以调用addAttch()添加附件
 * 创建AttachBean：new AttachBean(new File("..."), "fileName");
 */
public class Mail {
	private String from;//发件�?
	private StringBuilder toAddress = new StringBuilder();//收件�?
	private StringBuilder ccAddress = new StringBuilder();//抄�??
	private StringBuilder bccAddress = new StringBuilder();//暗�??
	
	private String subject;//主题
	private String content;//正文
	
	// 附件列表
	private List<AttachBean> attachList = new ArrayList<AttachBean>();
	
	public Mail() {}
	
	public Mail(String from, String to) {
		this(from, to, null, null);
	}
	
	public Mail(String from, String to, String subject, String content) {
		this.from = from;
		this.toAddress.append(to);
		this.subject = subject;
		this.content = content;
	}
	
	/**
	 * 返回发件�?
	 * @return
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	
	/**
	 * 返回发件�?
	 * @return
	 */
	public String getFrom() {
		return from;
	}
	
	/**
	 * 返回主题
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * 设置主题
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 获取主题内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置主题内容
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取收件�?
	 * @return
	 */
	public String getToAddress() {
		return toAddress.toString();
	}

	/**
	 * 获取抄�??
	 * @return
	 */
	public String getCcAddress() {
		return ccAddress.toString();
	}

	/**
	 * 获取暗�??
	 * @return
	 */
	public String getBccAddress() {
		return bccAddress.toString();
	}

	/**
	 * 添加收件�?,可以是多个收件人
	 * @param to
	 */
	public void addToAddress(String to) {
		if(this.toAddress.length() > 0) {
			this.toAddress.append(",");
		}
		this.toAddress.append(to);
	}

	/**
	 * 添加抄�?�人，可以是多个抄�?�人
	 * @param cc
	 */
	public void addCcAddress(String cc) {
		if(this.ccAddress.length() > 0) {
			this.ccAddress.append(",");
		}
		this.ccAddress.append(cc);
	}

	/**
	 * 添加暗�?�人，可以是多个暗�?�人
	 * @param bcc
	 */
	public void addBccAddress(String bcc) {
		if(this.bccAddress.length() > 0) {
			this.bccAddress.append(",");
		}
		this.bccAddress.append(bcc);
	}
	
	/**
	 * 添加附件，可以添加多个附�?
	 * @param attachBean
	 */
	public void addAttach(AttachBean attachBean) {
		this.attachList.add(attachBean);
	}
	
	/**
	 * 获取�?有附�?
	 * @return
	 */
	public List<AttachBean> getAttachs() {
		return this.attachList;
	}
}
