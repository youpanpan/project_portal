package com.chengxuunion.projectportal.business.email.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeUtility;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chengxuunion.component.mail.smtp.service.SmtpMailService;
import com.chengxuunion.projectportal.business.email.dao.EmailDao;
import com.chengxuunion.projectportal.business.email.model.Email;
import com.chengxuunion.projectportal.business.email.service.EmailService;
import com.chengxuunion.projectportal.business.email.util.MailPropertiesUtils;
import com.chengxuunion.projectportal.common.constant.Constants;
import com.chengxuunion.util.collection.CollectionUtils;
import com.chengxuunion.util.date.DateUtils;
import com.chengxuunion.util.id.IdGenerator;
import com.chengxuunion.util.string.StringUtils;
import com.github.pagehelper.PageHelper;

import sun.misc.BASE64Encoder;

/**
 * 邮件服务实现
 *
 * @author youpanpan
 * @date 2018年8月30日
 * @version V1.0
 */
@Service
public class EmailServiceImpl implements EmailService {

	/**
	 * 邮件DAO
	 */
	@Autowired
	private EmailDao emailDao;
	
	@Autowired
	private SmtpMailService smtpMailService;
	
	@Override
	public boolean checkEmailCode(Email email) {
		
		//注册邮件有效天数
		int dayNum = Integer.parseInt(MailPropertiesUtils.getInstance().getValue("register.email.day"));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email.getEmail());
		params.put("state", Constants.VALID);
		params.put("startDate", DateUtils.beforeDay(new Date(), dayNum));
		params.put("type", email.getType());
		
		//取第一条记录：最近的
		PageHelper.startPage(1, 1);
		List<Email> emails = emailDao.queryRecentlyEmails(params);
		if (CollectionUtils.isEmpty(emails)) {
			return false;
		}
		
		//判断验证码是否相同
		Email dbEmail = emails.get(0);
		if (StringUtils.isEquals(email.getCode(), dbEmail.getCode())) {
			return true;
		}
		
		return false;
	}


	@Override
	public int sendEmail(Email email) {
		
		//生成主键
		email.setId(IdGenerator.getInstance().nextId());
		
		//验证码
		String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
		email.setCode(verifyCode);
		
		//记录日志
		LoggerFactory.getLogger(this.getClass()).debug("新增邮件:" + email);
		
		//发送邮件
		String prefix = "";
		if (StringUtils.isEquals(email.getType(), Constants.EMAIL_TYPE_REGISTER)) {
			prefix = "register";
		} else if (StringUtils.isEquals(email.getType(), Constants.EMAIL_TYPE_RESET)) {
			prefix = "resetpassword";
		} 
		String emailSubject = "";
		try {
			sun.misc.BASE64Encoder base64 = new BASE64Encoder(); 
			emailSubject = MailPropertiesUtils.getInstance().getValue(prefix + ".subject");
			new String(base64.encode(emailSubject.getBytes("UTF-8"))); 
			emailSubject = MimeUtility.encodeText(emailSubject, "UTF-8", "B"); 
			System.out.println(emailSubject);
		} catch (UnsupportedEncodingException e1) {
			throw new RuntimeException(e1.getMessage());
		}
		String cid = "1000";
		String imagePath = this.getClass().getClassLoader().getResource("static/images/logo.png").getPath();
		String emailContent = String.format(MailPropertiesUtils.getInstance().getValue(prefix + ".template"), cid, verifyCode);
		
		boolean isSend = smtpMailService.sendImageMail(email.getEmail(), emailSubject, emailContent, cid, imagePath);
		
		if (!isSend) {
			throw new RuntimeException("发送邮件失败，请您确认邮件地址【"+ email.getEmail() +"】是否存在！");
		}
		
		Date nowDate = new Date();
		email.setCreateDate(nowDate);
		email.setUpdateDate(nowDate);
		email.setState(Constants.VALID);
		return emailDao.saveEmail(email);
	}


	@Override
	public int updateEmailState(Email email) {
		email.setState(Constants.INVALID);
		
		//记录日志
		LoggerFactory.getLogger(this.getClass()).debug("更新邮件：" + email);
		
		return emailDao.updateEmail(email);
	}

	
}
