package com.chengxuunion.component.sms.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.chengxuunion.component.sms.model.SmsProperties;

/**
 * 短信配置类
 *
 * @author yp2
 * @date 2018年11月20日
 * @version V1.0
 */
@Configuration
public class SmsConfig {

	private Logger logger = LoggerFactory.getLogger(SmsConfig.class);
	
	@Autowired
	private SmsProperties smsProperties;
	
	@Bean
	public IAcsClient getAcsClient() {
		//设置超时时间-可自行调整
		System.setProperty("sun.net.client.defaultConnectTimeout", smsProperties.getConnectionTimeout());
		System.setProperty("sun.net.client.defaultReadTimeout", smsProperties.getReadTimeout());
		//初始化ascClient需要的几个参数
		final String product = smsProperties.getProduct();//短信API产品名称（短信产品名固定，无需修改）
		final String domain = smsProperties.getDomain();//短信API产品域名（接口地址固定，无需修改）
		//替换成你的AK
		final String accessKeyId = smsProperties.getAccessKeyId();//你的accessKeyId,参考本文档步骤2
		final String accessKeySecret = smsProperties.getAccessKeySecret();//你的accessKeySecret，参考本文档步骤2
		//初始化ascClient,暂时不支持多region（请勿修改）
		IClientProfile profile = DefaultProfile.getProfile(smsProperties.getRegion(), accessKeyId,
		accessKeySecret);
		try {
			DefaultProfile.addEndpoint(smsProperties.getRegion(), smsProperties.getRegion(), product, domain);
		} catch (ClientException e) {
			logger.error("初始化短信接口服务出现异常", e);
		}
		IAcsClient acsClient = new DefaultAcsClient(profile);
		
		return acsClient;
	}
	
}
