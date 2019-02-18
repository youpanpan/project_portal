package com.chengxuunion.projectportal.business.project.dao;

import com.chengxuunion.projectportal.business.project.model.Project;
import com.chengxuunion.projectportal.business.project.model.request.ProjectPageParam;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author youpanpan
 * @Description:    项目Dao
 * @Date:创建时间: 2019-01-22 21:37:34
 * @Modified By:
 */
@Repository
public interface ProjectDao {

    /**
     * 查询项目列表
     *
     * @param projectPageParam  参数对象
     * @return  项目列表
     */
    List<Project> listProject(ProjectPageParam projectPageParam);

    /**
     * 根据主键查询单个项目对象
     *
     * @param id 主键
     * @return  单个项目对象
     */
    Project getProject(@Param("id") Long id);

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
    int deleteProject(@Param("id") Long id);
}