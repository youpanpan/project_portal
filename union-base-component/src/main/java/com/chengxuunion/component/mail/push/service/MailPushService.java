package com.chengxuunion.component.mail.push.service;

import com.chengxuunion.component.mail.push.model.MailPushResponse;

/**
 * 邮件推送服务接口
 *
 * @author yp2
 * @date 2018年12月9日
 * @version V1.0
 */
public interface MailPushService {
	
	/**
	 * 单一发送邮件
	 * 
	 * @param receiver	接收人邮件地址
	 * @param emailSubject	邮件主题
	 * @param emailContent	邮件内容
	 * @return
	 */
	MailPushResponse sendSingleMail(String receiver, String emailSubject, String emailContent);
	
	/**
	 * 批量发送邮件
	 * 
	 * @param receiversName	收件人列表名称（控制台设置）
	 * @param templateName	模版名称（控制台设置）
	 * @return
	 */
	MailPushResponse sendBatchMail(String receiversName, String templateName);

}
