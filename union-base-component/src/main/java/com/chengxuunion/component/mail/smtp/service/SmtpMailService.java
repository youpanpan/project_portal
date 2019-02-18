package com.chengxuunion.component.mail.smtp.service;

import com.chengxuunion.component.mail.smtp.model.SmtpProperties;

/**
 * SMTP邮件发送服务接口
 *
 * @author yp2
 * @date 2018年12月9日
 * @version V1.0
 */
public interface SmtpMailService {

	/**
	 * 发送文本邮件
	 * 
	 * @param receiver	收件人地址
	 * @param emailSubject	邮件主题
	 * @param emailContent	邮件内容
	 * @param emailContentType	邮件内容类型，支持纯文本:"text/plain;charset=utf-8";,带有Html格式的内容:"text/html;charset=utf-8" 
	 * @return	发送成功，返回true，否则返回false
	 */
	boolean sendTextMail(String receiver, String emailSubject, String emailContent, String emailContentType) ;
	
	/**
	 * 发送带图片内容的邮件，格式：<img src='cid:cid的值' width="266px" height="50px">
	 * 
	 * @param receiver	收件人地址
	 * @param emailSubject	邮件主题
	 * @param emailContent	邮件内容
	 * @param cid	图片内容ID
	 * @param filePath	图片文件本地路径
	 * @return	发送成功，返回true，否则返回false
	 */
	boolean sendImageMail(String receiver, String emailSubject, String emailContent, String cid, String filePath) ;

	/**
	 * 设置SMTP配置对象
	 *
	 * @param smtpProperties
	 */
	void setSmtpProperties(SmtpProperties smtpProperties);
	
}
