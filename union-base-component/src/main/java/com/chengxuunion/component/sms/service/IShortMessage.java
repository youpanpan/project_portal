package com.chengxuunion.component.sms.service;

import com.chengxuunion.component.sms.model.SendSmsResponseAdapter;

/**
 * 短信接口
 *
 * @author yp2
 * @date 2018年11月20日
 * @version V1.0
 */
public interface IShortMessage {
	
	/**
	 * 发送短信
	 * 
	 * @param phoneNumber	手机号码
	 * @param code	验证码
	 * @return
	 * @throws Exception
	 */
	SendSmsResponseAdapter sendMessage(String phoneNumber, String code) throws Exception;

}
