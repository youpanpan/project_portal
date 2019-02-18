package com.chengxuunion.component.oauth.common;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.chengxuunion.component.oauth.util.OAuthPropertiesUtils;


/**
 * 基础授权信息
 *
 * @author kutome
 * @date 2018年11月19日
 * @version V1.0
 */
public abstract class BaseAuthInfo {
	
	protected Map<String, String> params = OAuthPropertiesUtils.getInstance().getParamMap();
	
	public String getAuthUrl() {
		return templateReplace(OAuthPropertiesUtils.getInstance().getValue(getName() + ".authUrl"));
	}
	
	public String getTokenUrl(String code) {
		params.put("code", code);
		return templateReplace(OAuthPropertiesUtils.getInstance().getValue(getName() + ".tokenUrl"));
	}
	
	public String getClientId() {
		return OAuthPropertiesUtils.getInstance().getValue(getName() + ".clientId");
	}
	
	public String getClientSecret() {
		return OAuthPropertiesUtils.getInstance().getValue(getName() + ".clientSecret");
	}
	
	public String getRedirectUrl() {
		String key = getName() + ".redirectUrl";
		String value = OAuthPropertiesUtils.getInstance().getValue(key);
		params.put(key, value);
		return value;
	}
	
	public String getBasicUrl() {
		return OAuthPropertiesUtils.getInstance().getValue(getName() + ".basicUrl");
	}
	
	public String getUserInfoUrl(String accessToken) {
		params.put("accessToken", accessToken);
		return templateReplace(OAuthPropertiesUtils.getInstance().getValue(getName() + ".userInfoUrl"));
	}
	
	public abstract String getName();
	
	/**
	 * 使用params中配置的参数替换掉value中使用的变量，如果不存在，则不替换
	 * 
	 * @param value
	 * @param params
	 * @return
	 */
	protected String templateReplace(String value) {
		Pattern pattern = Pattern.compile("\\$\\{(.*?)\\}");
		Matcher matcher = pattern.matcher(value);
		
		StringBuffer sb = new StringBuffer();
		while(matcher.find()) {
			String key = matcher.group(1).trim();
			String keyValue = params.get(key);
			if (keyValue == null) {
				matcher.appendReplacement(sb, matcher.group(0));
				continue;
			}
			
			matcher.appendReplacement(sb, keyValue);
		}
		matcher.appendTail(sb);
		
		return sb.toString();
	}

}
