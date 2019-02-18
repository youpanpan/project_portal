package com.chengxuunion.component.mail.smtp.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * SMTP配置（发送邮件配置）
 *
 * @author yp2
 * @date 2018年12月9日
 * @version V1.0
 */
@Configuration
@ConfigurationProperties(prefix="smtp")
@PropertySource(value= {"classpath:smtp.properties"})
public class SmtpProperties {
	
	/**
	 * 传输协议:smtp
	 */
	private String protocol;
	
	/**
	 * 主机
	 */
	private String host;
	
	/**
	 * 端口
	 */
	private String port;
	
	/**
	 * 是否需要授权
	 */
	private String auth;
	
	/**
	 * 发送方地址
	 */
	private String from;
	
	/**
	 * 授权码
	 */
	private String authcode;

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}
	

}
