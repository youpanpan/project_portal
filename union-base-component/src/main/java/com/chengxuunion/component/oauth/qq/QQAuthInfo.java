package com.chengxuunion.component.oauth.qq;

import org.springframework.stereotype.Component;

import com.chengxuunion.component.oauth.common.BaseAuthInfo;
import com.chengxuunion.component.oauth.util.OAuthPropertiesUtils;


/**
 * qq授权信息
 *
 * @author yp2
 * @date 2018年11月27日
 * @version V1.0
 */
@Component("qqAuthInfo")
public class QQAuthInfo extends BaseAuthInfo {

	@Override
	public String getName() {
		return "qq";
	}

	public String getUserInfoUrl(String accessToken, String openId) {
		params.put("accessToken", accessToken);
		params.put("openId", openId);
		return templateReplace(OAuthPropertiesUtils.getInstance().getValue(getName() + ".userInfoUrl"));
	}
}
