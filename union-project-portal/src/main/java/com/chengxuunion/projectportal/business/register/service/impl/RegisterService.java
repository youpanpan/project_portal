package com.chengxuunion.projectportal.business.register.service.impl;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chengxuunion.projectportal.business.email.model.Email;
import com.chengxuunion.projectportal.business.email.service.EmailService;
import com.chengxuunion.projectportal.business.register.service.IRegisterService;
import com.chengxuunion.projectportal.business.role.model.Role;
import com.chengxuunion.projectportal.business.role.service.RoleService;
import com.chengxuunion.projectportal.business.url.model.Url;
import com.chengxuunion.projectportal.business.url.service.UrlService;
import com.chengxuunion.projectportal.business.user.model.User;
import com.chengxuunion.projectportal.business.user.service.UserService;
import com.chengxuunion.projectportal.business.userrole.model.UserRole;
import com.chengxuunion.projectportal.business.userrole.service.UserRoleService;
import com.chengxuunion.projectportal.common.constant.Constants;
import com.chengxuunion.projectportal.common.util.SessionUtils;
import com.chengxuunion.util.collection.MapUtils;
import com.chengxuunion.util.security.RSAUtils;
import com.chengxuunion.util.security.SecurityUtils;


/**
 * 注册服务实现
 *
 * @author kutome
 * @date 2018年8月30日
 * @version V1.0
 */
@Service
public class RegisterService implements IRegisterService {

	/**
	 * 邮件服务
	 */
	@Autowired
	private EmailService emailService;
	
	/**
	 * 用户服务
	 */
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UrlService urlService;
	
	@Override
	public void sendEmail(Email email) {
		
		//1.验证邮箱是否已被使用
		User user = userService.getUserByEmail(email.getEmail());
		if (user != null) {
			throw new RuntimeException("邮箱【"+ email.getEmail() +"】已被使用，请使用其它邮箱注册");
		}
		
		//2.发送邮件
		int res = emailService.sendEmail(email);
		if (res == Constants.NO_DATA) {
			throw new RuntimeException("发送邮件失败");
		}
	}

	@Override
	public void registerUser(Map<String, Object> params) {
		
		String email = MapUtils.getString(params, "email");
		//1.验证邮箱是否已被使用
		User user = userService.getUserByEmail(email);
		if (user != null) {
			throw new RuntimeException("邮箱【"+ email +"】已被使用，请使用其它邮箱注册");
		}
		
		//2.验证邮箱验证码是否正确
		String code = MapUtils.getString(params, "code");
		Email checkEmail = new Email();
		checkEmail.setCode(code);
		checkEmail.setEmail(email);
		checkEmail.setToken(MapUtils.getString(params, "token"));
		checkEmail.setType(Constants.EMAIL_TYPE_REGISTER);
		boolean isCheck = emailService.checkEmailCode(checkEmail);
		if (!isCheck) {
			throw new RuntimeException("邮箱验证码不正确");
		}
		
		//3.新增用户
		try {
			user = new User();
			user.setEmail(email);
			user.setUserName(MapUtils.getString(params, "userName"));
			user.setPassword(MapUtils.getString(params, "password"));
			String decryptText = new String(RSAUtils.decryptByPrivateKey(user.getPassword().getBytes()));
			user.setPassword(SecurityUtils.encryptMD5(decryptText));
			user.setStatus(Constants.ENABLE);
			
			userService.saveUser(user);
			
			//密码置空
			user.setPassword("");
			//4.登录
			List<UserRole> userRoleList = userRoleService.listUserRoleByUserId(user.getId());
	        user.setUserRoleList(userRoleList);
	        SessionUtils.setValue(Constants.USER, user);

	        // 获取用户能访问的URL列表
	        List<Url> menuList = urlService.listMenuUrlByUserId(user.getId());
	        SessionUtils.setValue(Constants.MENU_LIST, menuList);
			
			//5.修改邮件状态
			emailService.updateEmailState(checkEmail);
			
			//6.新增用户角色
			Role role = roleService.getRoleByRoleCode(Constants.ROLE_DEVELOPER);
			if (role == null) {
				throw new RuntimeException("系统没有"+ Constants.ROLE_DEVELOPER +"角色，请联系管理员解决！");
			}
			UserRole userRole = new UserRole();
			userRole.setUserId(user.getId());
			userRole.setRoleId(role.getId());
			userRoleService.saveUserRole(userRole);
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
