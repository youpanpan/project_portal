package com.chengxuunion.component.oss.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aliyun.oss.OSSClient;
import com.chengxuunion.component.oss.model.AliOSSProperties;

/**
 * 阿里云OSS配置
 *
 * @author yp2
 * @date 2018年12月7日
 * @version V1.0
 */
@Configuration
public class AliOSSConfig {

	@Autowired
	private AliOSSProperties aliOSSProperties;
	
	/**
	 * 注入阿里云OSS客户端对象
	 * 
	 * @return
	 */
	@Bean
	public OSSClient ossClient() {
		OSSClient ossClient = new OSSClient(
			aliOSSProperties.getEndPoint(), 
			aliOSSProperties.getAccessKeyId(), 
			aliOSSProperties.getAccessKeySecret()
		);
		
		return ossClient;
	}
	
}
