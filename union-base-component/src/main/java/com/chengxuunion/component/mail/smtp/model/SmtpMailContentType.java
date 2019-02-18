package com.chengxuunion.component.mail.smtp.model;

/**
 * SMTP邮件内容格式类型
 *
 * @author yp2
 * @date 2018年12月9日
 * @version V1.0
 */
public enum SmtpMailContentType {
	
	/**
	 * 文本
	 */
	TEXT_PLAIN("text/plain;charset=utf-8"),
	
	/**
	 * HTML
	 */
	TEXT_HTML("text/html;charset=utf-8");
	
	private String contentType;
	
	private SmtpMailContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentType() {
		return contentType;
	}
	
}
