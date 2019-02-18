package com.chengxuunion.component.oauth.util;

import java.io.IOException;

import org.slf4j.LoggerFactory;

import com.chengxuunion.util.properties.PropertiesReader;

/**
 * oauth.properties配置文件读取工具类
 *
 * @author kutome
 * @date 2018年11月19日
 * @version V1.0
 */
public class OAuthPropertiesUtils {

	/**
	 * properties文件读取器
	 */
	private PropertiesReader reader;
	
	private OAuthPropertiesUtils() {
		try {
			reader = new PropertiesReader("/oauth.properties");
		} catch (IOException e) {
			LoggerFactory.getLogger(this.getClass()).error("初始化oauth.properties文件读取器出现异常", e);
		}
	}
	
	/**
	 * 内部静态类，实现单例
	 *
	 * @author kutome
	 * @date 2018年8月29日
	 * @version V1.0
	 */
	public static class Singleton {
		private static OAuthPropertiesUtils instance = new OAuthPropertiesUtils();
	}

	/**
	 * 获取PropertiesReader对象
	 * 
	 * @return
	 */
	public static PropertiesReader getInstance() {
		return Singleton.instance.reader;
	}

}
