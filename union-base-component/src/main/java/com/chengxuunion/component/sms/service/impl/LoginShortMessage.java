package com.chengxuunion.component.sms.service.impl;

import org.springframework.stereotype.Component;


/**
 * 登录短信服务
 *
 * @author yp2
 * @date 2018年11月20日
 * @version V1.0
 */
@Component
public class LoginShortMessage extends AbstractShortMessage {

	@Override
	public String getTemplateKey() {
		return "loginCode";
	}

}
