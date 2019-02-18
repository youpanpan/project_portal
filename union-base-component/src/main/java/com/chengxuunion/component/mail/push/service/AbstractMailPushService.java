package com.chengxuunion.component.mail.push.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.BatchSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.chengxuunion.component.mail.push.model.MailPushProperties;

/**
 * 邮件推送服务抽象类：获取IAcsClient对象
 *
 * @author yp2
 * @date 2018年12月9日
 * @version V1.0
 */
public abstract class AbstractMailPushService implements MailPushService {

	@Autowired
	protected MailPushProperties mailPushProperties;
	
	/**
	 * 获取操作对象
	 * 
	 * @return
	 * @throws ClientException
	 */
	public IAcsClient getAcsClient() throws ClientException {
		IClientProfile profile = DefaultProfile.getProfile(mailPushProperties.getRegion(), 
				mailPushProperties.getAccessKeyId(), mailPushProperties.getAccessKeySecret());
		
		// 如果不是cn-hangzhou,需要额外处理
		if (!"cn-hangzhou".equals(mailPushProperties.getRegion())) {
			DefaultProfile.addEndpoint(mailPushProperties.getDomain(), mailPushProperties.getRegion(), 
					mailPushProperties.getProduct(), mailPushProperties.getDomain());
		} 
		
		return new DefaultAcsClient(profile);
	}
	
	/**
	 * 除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
	 * 
	 * @param request
	 */
	public void setVersion(SingleSendMailRequest request) {
		if (!"cn-hangzhou".equals(mailPushProperties.getRegion())) {
			request.setVersion("2017-06-22");
		}
	}
	
	/**
	 * 除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
	 * 
	 * @param request
	 */
	public void setVersion(BatchSendMailRequest request) {
		if (!"cn-hangzhou".equals(mailPushProperties.getRegion())) {
			request.setVersion("2017-06-22");
		}
	}
	
}
