package com.chengxuunion.projectportal.business.userrole.service;



import java.util.List;

import com.chengxuunion.projectportal.business.user.model.User;
import com.chengxuunion.projectportal.business.userrole.model.UserRole;
import com.chengxuunion.projectportal.business.userrole.model.request.UserRolePageParam;
import com.chengxuunion.projectportal.common.model.PageResult;


/**
 * @Author youpanpan
 * @Description:    用户角色服务接口
 * @Date:创建时间: 2019-01-17 16:59:14
 * @Modified By:
 */
public interface UserRoleService {

    /**
     * 查询用户角色分页列表
     *
     * @param userRolePageParam  参数对象
     * @return  用户角色列表
     */
    PageResult<UserRole> listUserRolePage(UserRolePageParam userRolePageParam);

    /**
     * 查询用户角色信息
     *
     * @param userId
     * @return
     */
    List<UserRole> listUserRoleByUserId(Long userId);

    /**
     * 根据主键查询单个用户角色对象
     *
     * @param id 主键
     * @return  单个用户角色对象
     */
    UserRole getUserRole(Long id);

    /**
     * 保存用户角色对象
     *
     * @param userRole 用户角色对象
     * @return  保存影响的记录数
     */
    int saveUserRole(UserRole userRole);

    /**
     * 批量保存用户角色
     *
     * @param user
     * @return
     */
    int saveUserRoleBatch(User user);

    /**
     * 更新用户角色对象
     *
     * @param userRole 用户角色对象
     * @return  更新影响的记录数
     */
    int updateUserRole(UserRole userRole);

    /**
     * 根据主键删除用户角色
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    int deleteUserRole(Long id);

}