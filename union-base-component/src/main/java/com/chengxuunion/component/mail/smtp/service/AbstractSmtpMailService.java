package com.chengxuunion.component.mail.smtp.service;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chengxuunion.component.mail.smtp.model.SmtpProperties;
import com.sun.mail.util.MailSSLSocketFactory;

/**
 * SMTP邮件发送服务抽象类：创建邮件会话 
 *
 * @author yp2
 * @date 2018年12月9日
 * @version V1.0
 */
public abstract class AbstractSmtpMailService implements SmtpMailService{

	private static Logger logger = LoggerFactory.getLogger(AbstractSmtpMailService.class);
	
	@Autowired
	protected SmtpProperties smtpProperties;
	
	
	/**
	 * 创建邮件会话
	 * 
	 * @return
	 * @throws GeneralSecurityException 
	 */
	protected Session newSession() throws GeneralSecurityException {
		//跟smtp服务器建立一个连接
        Properties p = new Properties();
        
        //设置邮件服务器主机名
        p.setProperty("mail.smtp.host", smtpProperties.getHost());
        
        //邮件服务器主机端口号
        p.setProperty("mail.smtp.port", smtpProperties.getPort());  
        
        //发送服务器需要身份验证,要采用指定用户名密码的方式去认证
        p.setProperty("mail.smtp.auth", smtpProperties.getAuth());
        
        //发送邮件协议名称
        p.setProperty("mail.transport.protocol", smtpProperties.getProtocol());
        
        //开启SSL加密，否则会失败
        MailSSLSocketFactory sf;
        Session session = null;
		try {
			sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
			p.put("mail.smtp.starttls.enable", "true");
			p.put("mail.smtp.ssl.enable", "true");
			p.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			p.setProperty("mail.smtp.socketFactory.fallback", "false");
			p.setProperty("mail.smtp.socketFactory.port", smtpProperties.getPort());
			
			// 创建session
			session = Session.getDefaultInstance(p, new Authenticator() {
			    protected PasswordAuthentication getPasswordAuthentication() {
			        //用户名可以用QQ账号也可以用邮箱的别名:第一个参数为邮箱账号,第二个为授权码
			        PasswordAuthentication pa = new PasswordAuthentication(smtpProperties.getFrom(), smtpProperties.getAuthcode());
			        return pa;
			    }
			});
		} catch (GeneralSecurityException e) {
			logger.error("初始化SMTP会话出现异常", e);
			throw e;
		}
		return session;
	}

	@Override
	public void setSmtpProperties(SmtpProperties smtpProperties) {
		this.smtpProperties = smtpProperties;
	}
}
