package com.chengxuunion.component.sms.model;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;

/**
 * 发送短信响应实体
 *
 * @author yp2
 * @date 2018年11月20日
 * @version V1.0
 */
public class SendSmsResponseAdapter {

	/**
	 * 真实响应（阿里云短信api）
	 */
	private SendSmsResponse sendSmsResponse;
	
	public SendSmsResponseAdapter(SendSmsResponse sendSmsResponse) {
		this.sendSmsResponse = sendSmsResponse;
	}
	
	/**
	 * 是否有效
	 * 
	 * @return
	 */
	public boolean isValid() {
		return sendSmsResponse != null;
	}
	
	/**
	 * 请求ID
	 * @return
	 */
	public String getRequestId() {
		return sendSmsResponse.getRequestId();
	}
	
	/**
	 * 状态码-返回OK代表请求成功,其他错误码详见错误码列表{@link SmsCode}
	 * @return
	 */
	public String getCode() {
		return sendSmsResponse.getCode();
	}
	
	/**
	 * 状态码的描述,详见{@link SmsCode}
	 * @return
	 */
	public String getMessage() {
		return sendSmsResponse.getMessage();
	}
	
	/**
	 * 发送回执ID,可根据该ID查询具体的发送状态
	 * @return
	 */
	public String getBizId() {
		return sendSmsResponse.getBizId();
	}
}
