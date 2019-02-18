package com.chengxuunion.component.sms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.chengxuunion.component.sms.model.SendSmsResponseAdapter;
import com.chengxuunion.component.sms.model.SmsProperties;
import com.chengxuunion.component.sms.service.IShortMessage;

/**
 * 短信服务抽象
 *
 * @author yp2
 * @date 2018年11月20日
 * @version V1.0
 */
public abstract class AbstractShortMessage implements IShortMessage {

	@Autowired
	private IAcsClient acsClient;
	
	@Autowired
	private SmsProperties smsProperties;
	
	@Override
	public SendSmsResponseAdapter sendMessage(String phoneNumber, String code) throws Exception {
		SendSmsRequest sendSmsRequest = getSendSmsRequest(phoneNumber, code);
		
		//请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(sendSmsRequest);
		
		return new SendSmsResponseAdapter(sendSmsResponse);
	}
	
	/**
	 * 组装短信请求对象
	 * 
	 * @param phoneNumber	手机号
	 * @param code	验证码
	 * @return
	 */
	public SendSmsRequest getSendSmsRequest(String phoneNumber, String code) {
		//组装请求对象
		SendSmsRequest request = new SendSmsRequest();
		
		//使用post提交
		request.setMethod(MethodType.POST);
		
		//必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
		request.setPhoneNumbers(phoneNumber);
		
		//必填:短信签名-可在短信控制台中找到
		request.setSignName(smsProperties.getSignName());
		
		//必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
		request.setTemplateCode(smsProperties.getTemplate().get(getTemplateKey()));
		
		//可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		//友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
		request.setTemplateParam("{\"code\":\""+ code +"\"}");
		
		//可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
		//request.setSmsUpExtendCode("90997");
		//可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId("yourOutId");
		
		return request;
	}
	
	/**
	 * 模版配置的key
	 * @return
	 */
	public abstract String getTemplateKey();

}
