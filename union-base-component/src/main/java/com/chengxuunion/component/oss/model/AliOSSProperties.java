package com.chengxuunion.component.oss.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 阿里云OSS配置
 *
 * @author yp2
 * @date 2018年12月7日
 * @version V1.0
 */
@Configuration
@ConfigurationProperties(prefix="oss")
@PropertySource(value= {"classpath:oss.properties"})
public class AliOSSProperties {
	
	/**
	 * 地址信息
	 * 不同Region地址不同
	 */
	private String endPoint;
	
	/**
	 * 访问KEY
	 */
	private String accessKeyId;
	
	/**
	 * 访问KEY Secret
	 */
	private String accessKeySecret;
	
	/**
	 * 存储的容器（Bucket）名称：
	 */
	private String bucketName;

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
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

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	
}
