package com.chengxuunion.projectportal.business.project.model.request;

import com.chengxuunion.projectportal.common.model.PageParam;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-22 21:37:34
 * @Modified By:
 */
public class ProjectPageParam extends PageParam{

	/**
	 * 项目名称
	 */
	private String projectName;

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 是否官方项目
	 */
	private String official;

	/**
	 * 状态
	 */
	private String status;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getOfficial() {
		return official;
	}

	public void setOfficial(String official) {
		this.official = official;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}