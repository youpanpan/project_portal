package com.chengxuunion.projectportal.business.email.service;

import com.chengxuunion.projectportal.business.email.model.Email;

/**
 * @Author youpanpan
 * @Description:    邮件服务接口
 * @Date:创建时间: 2019年1月26日
 * @Modified By:
 */
public interface EmailService {

	/**
	 * 验证邮件验证码是否正确
	 * 
	 * @param email
	 * @return
	 */
	boolean checkEmailCode(Email email);
	
	/**
	 * 发送邮件记录
	 * 
	 * @param email
	 * @return
	 */
	int sendEmail(Email email);
	
	/**
	 * 更新邮件状态
	 * 
	 * @param email
	 * @return
	 */
	int updateEmailState(Email email);
	
}
