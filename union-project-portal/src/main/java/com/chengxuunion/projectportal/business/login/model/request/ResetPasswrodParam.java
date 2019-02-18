package com.chengxuunion.projectportal.business.login.model.request;

/**
 * @Author youpanpan
 * @Description:    重置密码参数实体
 * @Date:创建时间: 2019年1月27日
 * @Modified By:
 */
public class ResetPasswrodParam {
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 邮箱验证码
	 */
	private String code;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * token
	 */
	private String token;
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
