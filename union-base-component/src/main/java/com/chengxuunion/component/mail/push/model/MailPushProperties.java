package com.chengxuunion.component.mail.push.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 阿里云邮件推送配置
 *
 * @author yp2
 * @date 2018年12月9日
 * @version V1.0
 */
@Configuration
@ConfigurationProperties(prefix="mailpush")
@PropertySource(value= {"classpath:mailpush.properties"})
public class MailPushProperties {
	
	/**
	 * 产品名
	 */
	private String product;

	/**
	 * 产品域名
	 */
	private String domain;
	
	/**
	 * 区域
	 */
	private String region;
	
	/**
	 * 访问key
	 */
	private String accessKeyId;
	
	/**
	 * 访问key secret
	 */
	private String accessKeySecret;
	
	/**
	 * 发信地址
	 */
	private String from;
	
	/**
	 * 批量邮件的发信地址
	 */
	private String batchFrom;
	
	/**
	 * 发信人昵称
	 */
	private String alias;
	
	/**
	 * 控制台创建的标签
	 */
	private String tagName;

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}
	
	public String getBatchFrom() {
		return batchFrom;
	}

	public void setBatchFrom(String batchFrom) {
		this.batchFrom = batchFrom;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
}
