package com.chengxuunion.projectportal.business.login.service;

import com.chengxuunion.projectportal.business.email.model.Email;
import com.chengxuunion.projectportal.business.login.model.request.LoginParam;
import com.chengxuunion.projectportal.business.login.model.request.ResetPasswrodParam;

/**
 * @Author youpanpan
 * @Description: 	登录服务接口
 * @Date:创建时间: 2019年1月26日
 * @Modified By:
 */
public interface LoginService {
	
	/**
	 * 登录
	 * 
	 * @param loginParam
	 */
	void login(LoginParam loginParam);
	
	/**
	 * 退出登录
	 */
	void logout();
	
	/**
	 * 重置密码
	 * 
	 * @param resetPasswrodParam
	 */
	void resetPassword(ResetPasswrodParam resetPasswrodParam);
	
	/**
	 * 发送邮件
	 * 
	 * @param email
	 */
	void sendEmail(Email email);

}
