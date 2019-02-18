package com.chengxuunion.component.sms.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
 * 短信配置
 *
 * @author yp2
 * @date 2018年11月20日
 * @version V1.0
 */
@Configuration
@ConfigurationProperties(prefix="sms")
@PropertySource(value= {"classpath:sms.properties"})
public class SmsProperties {

	private String product;

	private String domain;
	
	private String region;
	
	private String accessKeyId;
	
	private String accessKeySecret;
	
	private String signName;
	
	private Map<String, String> template = new HashMap<String, String>();
	
	private String connectionTimeout;

	private String readTimeout;
	
	private String isTest;
	
	public String getProduct() {
		return product;
	}

	public String getDomain() {
		return domain;
	}

	public String getRegion() {
		return region;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public String getSignName() {
		return signName;
	}

	public Map<String, String> getTemplate() {
		return template;
	}

	public String getConnectionTimeout() {
		return connectionTimeout;
	}

	public String getReadTimeout() {
		return readTimeout;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public void setTemplate(Map<String, String> template) {
		this.template = template;
	}

	public void setConnectionTimeout(String connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public void setReadTimeout(String readTimeout) {
		this.readTimeout = readTimeout;
	}

	public String getIsTest() {
		return isTest;
	}

	public void setIsTest(String isTest) {
		this.isTest = isTest;
	}
	
}
