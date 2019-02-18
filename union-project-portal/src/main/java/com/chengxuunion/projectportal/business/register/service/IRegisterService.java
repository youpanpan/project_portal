package com.chengxuunion.projectportal.business.register.service;

import java.util.Map;

import com.chengxuunion.projectportal.business.email.model.Email;

/**
 * 注册服务接口
 *
 * @author kutome
 * @date 2018年8月30日
 * @version V1.0
 */
public interface IRegisterService {

	/**
	 * 发送注册邮件
	 * 
	 * @param email
	 * @return
	 */
	void sendEmail(Email email);
	
	/**
	 * 注册
	 * 
	 * @param params
	 */
	void registerUser(Map<String, Object> params);
	
}
