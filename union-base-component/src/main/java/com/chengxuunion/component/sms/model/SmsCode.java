package com.chengxuunion.component.sms.model;

/**
 * 短信错误码
 *
 * @author yp2
 * @date 2018年11月20日
 * @version V1.0
 */
public enum SmsCode {
	
	OK("OK", "请求成功"),
	
	RAM_PERMISSION_DENY("isp.RAM_PERMISSION_DENY", "RAM权限DENY"),
	
	OUT_OF_SERVICE("isv.OUT_OF_SERVICE", "业务停机"),
	
	PRODUCT_UN_SUBSCRIPT("isv.PRODUCT_UN_SUBSCRIPT", "未开通云通信产品的阿里云客户"),
	
	PRODUCT_UNSUBSCRIBE("isv.PRODUCT_UNSUBSCRIBE", "产品未开通"),
	
	ACCOUNT_NOT_EXISTS("isv.ACCOUNT_NOT_EXISTS", "账户不存在"),
	
	ACCOUNT_ABNORMAL("isv.ACCOUNT_ABNORMAL", "账户异常"),
	
	SMS_TEMPLATE_ILLEGAL("isv.SMS_TEMPLATE_ILLEGAL", "短信模板不合法"),
	
	SMS_SIGNATURE_ILLEGAL("isv.SMS_SIGNATURE_ILLEGAL", "短信签名不合法"),
	
	INVALID_PARAMETERS("isv.INVALID_PARAMETERS", "参数异常"),
	
	SYSTEM_ERROR("isp.SYSTEM_ERROR", "系统错误"),
	
	MOBILE_NUMBER_ILLEGAL("isv.MOBILE_NUMBER_ILLEGAL", "非法手机号"),
	
	MOBILE_COUNT_OVER_LIMIT("isv.MOBILE_COUNT_OVER_LIMIT", "手机号码数量超过限制"),
	
	TEMPLATE_MISSING_PARAMETERS("isv.TEMPLATE_MISSING_PARAMETERS", "模板缺少变量"),
	
	BUSINESS_LIMIT_CONTROL("isv.BUSINESS_LIMIT_CONTROL", "业务限流"),
	
	INVALID_JSON_PARAM("isv.INVALID_JSON_PARAM", "JSON参数不合法，只接受字符串值"),
	
	BLACK_KEY_CONTROL_LIMIT("isv.BLACK_KEY_CONTROL_LIMIT", "黑名单管控"),
	
	PARAM_LENGTH_LIMIT("isv.PARAM_LENGTH_LIMIT", "参数超出长度限制"),
	
	PARAM_NOT_SUPPORT_URL("isv.PARAM_NOT_SUPPORT_URL", "不支持URL"),
	
	AMOUNT_NOT_ENOUGH("isv.AMOUNT_NOT_ENOUGH", "账户余额不足");
	
	/**
	 * Code
	 */
	private String code;
	
	/**
	 * 描述
	 */
	private String detail;

	private SmsCode(String code, String detail) {
		this.code = code;
		this.detail = detail;
	}
	
	/**
	 * 根据Code获取错误码信息
	 * 
	 * @param code
	 * @return
	 */
	public static SmsCode getSmsCode(String code) {
		SmsCode[] smsCodes = SmsCode.values();
		for (SmsCode smsCode : smsCodes) {
			if (smsCode.getCode().equals(code)) {
				return smsCode;
			}
		}
		
		return null;
	}

	public String getCode() {
		return code;
	}

	public String getDetail() {
		return detail;
	}

}
