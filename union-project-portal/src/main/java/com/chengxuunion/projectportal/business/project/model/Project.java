package com.chengxuunion.projectportal.business.project.model;

import java.util.Date;

import com.chengxuunion.projectportal.business.user.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Author youpanpan
 * @Description:    项目
 * @Date:创建时间: 2019-01-22 21:52:12
 * @Modified By:
 */
public class Project {

    
    /**
     * 序号，主键
     * 是否允许为空：NO
     */
    private Long id;

    /**
     * 项目名称
     * 是否允许为空：YES
     */
    private String projectName;

    /**
     * 项目描述
     * 是否允许为空：YES
     */
    private String projectDesc;

    /**
     * 项目地址
     * 是否允许为空：YES
     */
    private String projectUrl;

    /**
     * 项目图片地址
     * 是否允许为空：YES
     */
    private String projectImage;

    /**
     * 创建时间
     * 是否允许为空：YES
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//进行格式化转换
    private Date createDate;

    /**
     * 状态，0：禁用，启用
     * 是否允许为空：YES
     */
    private String status;

    /**
     * 排序号
     * 是否允许为空：YES
     */
    private Integer orderNum;

    /**
     * 背景颜色
     */
    private String bgColor;

    /**
     * 是否官方项目，0：否，1：是
     */
    private String official;

    /**
     * 创建用户ID
     */
    private Long createUserId;

    /**
     * 创建用户对象
     */
    private User user;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    public String getProjectImage() {
        return projectImage;
    }

    public void setProjectImage(String projectImage) {
        this.projectImage = projectImage;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getOfficial() {
        return official;
    }

    public void setOfficial(String official) {
        this.official = official;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}