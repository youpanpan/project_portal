package com.chengxuunion.component.mail.push.model;

/**
 * 邮件推送响应信息
 *
 * @author yp2
 * @date 2018年12月9日
 * @version V1.0
 */
public class MailPushResponse {
	
	/**
	 * 是否成功,true标识发送成功
	 */
	private Boolean isSuccess;
	
	private String requestId;
	
	private String envId;
	
	public MailPushResponse() {
		
	}

	public MailPushResponse(Boolean isSuccess, String requestId, String envId) {
		super();
		this.isSuccess = isSuccess;
		this.requestId = requestId;
		this.envId = envId;
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getEnvId() {
		return envId;
	}

	public void setEnvId(String envId) {
		this.envId = envId;
	}
	
}
