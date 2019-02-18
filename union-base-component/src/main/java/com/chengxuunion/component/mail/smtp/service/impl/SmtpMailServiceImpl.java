package com.chengxuunion.component.mail.smtp.service.impl;

import java.io.File;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chengxuunion.component.mail.smtp.model.SmtpMailContentType;
import com.chengxuunion.component.mail.smtp.service.AbstractSmtpMailService;
import com.chengxuunion.component.mail.smtp.service.SmtpMailService;

/**
 * SMTP发送邮件服务实现
 *
 * @author yp2
 * @date 2018年12月9日
 * @version V1.0
 */
@Service
public class SmtpMailServiceImpl extends AbstractSmtpMailService implements SmtpMailService {
	
	/**
	 * 日志记录对象
	 */
	private static Logger logger = LoggerFactory.getLogger(SmtpMailServiceImpl.class);
	
	@Override
	public boolean sendTextMail(String receiver, String emailSubject, String emailContent, String emailContentType) {
		try {
			Session session = newSession();
			
			//声明一个Message对象(代表一封邮件),从session中创建
            MimeMessage msg = new MimeMessage(session);
            //邮件信息封装
            //1发件人
            msg.setFrom(new InternetAddress(smtpProperties.getFrom()));
            
            //2收件人
            msg.setRecipient(RecipientType.TO, new InternetAddress(receiver));

            //3邮件内容:主题、内容
            msg.setSubject(emailSubject);
            msg.setContent(emailContent,emailContentType);//发html格式的文本
            
            //发送动作
            Transport.send(msg);
            
            return true;
		} catch (Exception e) {
			logger.error("发送"+ emailContentType +"格式的邮件["+ smtpProperties.getFrom() +"->"+ receiver +":"+ emailContent +"]发生异常", e);
			return false;
		}
	}

	@Override
	public boolean sendImageMail(String receiver, String emailSubject, String emailContent, String cid, String filePath) {
		
		try {
			Session session = newSession();
			
			//声明一个Message对象(代表一封邮件),从session中创建
			MimeMessage msg = new MimeMessage(session);
			//邮件信息封装
			//1发件人
			msg.setFrom(new InternetAddress(smtpProperties.getFrom()));
			
			//2收件人
			msg.setRecipient(RecipientType.TO, new InternetAddress(receiver));

			//3邮件内容:主题、内容
			msg.setSubject(emailSubject);
			
			//创建图片"节点"
			MimeBodyPart image = new MimeBodyPart();
			//读取本地文件
			DataHandler dh = new DataHandler(new FileDataSource(new File(filePath)));
			//将图片数据添加到"节点"
			image.setDataHandler(dh);
			// 为"节点"设置一个唯一编号（在文本"节点"将引用该ID）
			image.setContentID(cid);    
			
			//创建文本"节点"
			MimeBodyPart text = new MimeBodyPart();
			text.setContent(emailContent, SmtpMailContentType.TEXT_HTML.getContentType()); 
			
			//（文本+图片）设置 文本 和 图片"节点"的关系（将 文本 和 图片"节点"合成一个混合"节点"）
			MimeMultipart mm_text_image = new MimeMultipart();
			mm_text_image.addBodyPart(text);
			mm_text_image.addBodyPart(image);
			mm_text_image.setSubType("related");    // 关联关系
			
			msg.setContent(mm_text_image);
			
			//发送动作
			Transport.send(msg);
			
			return true;
		} catch (Exception e) {
			logger.error("发送带图片的邮件["+ smtpProperties.getFrom() +"->"+ receiver +":"+ emailContent +"]发生异常", e);
			return false;
		}
	}

}
