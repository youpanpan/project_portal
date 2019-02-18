package com.chengxuunion.projectportal.business.project.service;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.chengxuunion.projectportal.business.project.model.Project;
import com.chengxuunion.projectportal.business.project.model.request.ProjectPageParam;
import com.chengxuunion.projectportal.common.model.PageResult;


/**
 * @Author youpanpan
 * @Description:    项目服务接口
 * @Date:创建时间: 2019-01-22 21:37:34
 * @Modified By:
 */
public interface ProjectService {

    /**
     * 查询项目分页列表
     *
     * @param projectPageParam  参数对象
     * @return  项目列表
     */
    PageResult<Project> listProjectPage(ProjectPageParam projectPageParam);

    /**
     * 查询用户能访问的项目列表
     *
     * @param projectPageParam
     * @return
     */
    List<Project> listProject(ProjectPageParam projectPageParam);

    /**
     * 根据主键查询单个项目对象
     *
     * @param id 主键
     * @return  单个项目对象
     */
    Project getProject(Long id);

    /**
     * 保存项目对象
     *
     * @param project 项目对象
     * @return  保存影响的记录数
     */
    int saveProject(Project project);

    /**
     * 更新项目对象
     *
     * @param project 项目对象
     * @return  更新影响的记录数
     */
    int updateProject(Project project);

    /**
     * 根据主键删除项目
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    int deleteProject(Long id);
    
    /**
     * 上传项目图片
     *
     * @param file
     * @return
     */
    Map<String, Object> uploadProjectImage(MultipartFile file);
    
    /**
     * 下载项目图片
     *
     * @param id
     * @param response
     * @return
     */
    Boolean downloadProjectImage(Long id, HttpServletResponse response);

}