package com.chengxuunion.projectportal.business.login.model.request;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-21 11:22
 * @Modified By:
 */
public class LoginParam {

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;
    
    private String token;

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
