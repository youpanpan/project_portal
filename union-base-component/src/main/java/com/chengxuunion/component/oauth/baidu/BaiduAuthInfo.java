package com.chengxuunion.component.oauth.baidu;

import org.springframework.stereotype.Component;

import com.chengxuunion.component.oauth.common.BaseAuthInfo;


/**
 * 百度授权信息
 *
 * @author kutome
 * @date 2018年11月19日
 * @version V1.0
 */
@Component
public class BaiduAuthInfo extends BaseAuthInfo {

	@Override
	public String getName() {
		return "baidu";
	}
	
}
