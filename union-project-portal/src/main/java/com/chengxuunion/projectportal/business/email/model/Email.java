package com.chengxuunion.projectportal.business.email.model;

import java.util.Date;

/**
 * 邮件实体
 *
 * @author youpanpan
 * @date 2018年8月30日
 * @version V1.0
 */
public class Email {

	/**
	 * 序号，主键
	 */
	private Long id;
	
	/**
	 * 邮箱地址
	 */
	private String email;
	
	/**
	 * 类型，1：注册，2：找回密码
	 */
	private String type;
	
	/**
	 * 令牌，安全设置
	 */
	private String token;
	
	/**
	 * 验证码
	 */
	private String code;
	
	/**
	 * 创建人
	 */
	private String createUser;
	
	/**
	 * 创建人姓名
	 */
	private String createUserName;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 更新人
	 */
	private String updateUser;
	
	/**
	 * 更新时间
	 */
	private Date updateDate;
	
	/**
	 * 排序号
	 */
	private Integer orderNum;
	
	/**
	 * 状态，1：有效，0：无效
	 */
	private String state;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
