package com.chengxuunion.projectportal.business.login.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chengxuunion.projectportal.business.email.model.Email;
import com.chengxuunion.projectportal.business.email.service.EmailService;
import com.chengxuunion.projectportal.business.login.model.request.LoginParam;
import com.chengxuunion.projectportal.business.login.model.request.ResetPasswrodParam;
import com.chengxuunion.projectportal.business.login.service.LoginService;
import com.chengxuunion.projectportal.business.url.model.Url;
import com.chengxuunion.projectportal.business.url.service.UrlService;
import com.chengxuunion.projectportal.business.user.model.User;
import com.chengxuunion.projectportal.business.user.service.UserService;
import com.chengxuunion.projectportal.business.userrole.model.UserRole;
import com.chengxuunion.projectportal.business.userrole.service.UserRoleService;
import com.chengxuunion.projectportal.common.constant.Constants;
import com.chengxuunion.projectportal.common.util.SessionUtils;
import com.chengxuunion.util.security.RSAUtils;
import com.chengxuunion.util.security.SecurityUtils;
import com.chengxuunion.util.string.StringUtils;

/**
 * @Author youpanpan
 * @Description:    登录服务实现
 * @Date:创建时间: 2019年1月26日
 * @Modified By:
 */
@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UrlService urlService;
    
    @Autowired
    private EmailService emailService;
	
	@Override
    public void login(LoginParam loginParam) {
        User user = userService.getUserByEmail(loginParam.getEmail());
        if (user == null) {
            throw new RuntimeException("用户/密码错误");
        }

        String decryptText = "";
		try {
			decryptText = new String(RSAUtils.decryptByPrivateKey(loginParam.getPassword().getBytes()));
		} catch (Exception e) {
			throw new RuntimeException("密码格式不对");
		}
        String password = SecurityUtils.encryptMD5(decryptText);
        if (!StringUtils.isEquals(password, user.getPassword())) {
            throw new RuntimeException("用户/密码错误");
        }

        user.setPassword("");
        List<UserRole> userRoleList = userRoleService.listUserRoleByUserId(user.getId());
        user.setUserRoleList(userRoleList);
        SessionUtils.setValue(Constants.USER, user);
		user.setAdmin(SessionUtils.isAdmin());
		SessionUtils.setValue(Constants.USER, user);

        // 获取用户能访问的URL列表
        List<Url> menuList = urlService.listMenuUrlByUserId(user.getId());
        SessionUtils.setValue(Constants.MENU_LIST, menuList);
    }

    @Override
    public void logout() {
        SessionUtils.remove(Constants.USER);
        SessionUtils.remove(Constants.URL_LIST);
        SessionUtils.remove(Constants.MENU_LIST);
    }

	@Override
	public void resetPassword(ResetPasswrodParam resetPasswrodParam) {
		String email = resetPasswrodParam.getEmail();
		try {
			//查询用户
			User user = userService.getUserByEmail(email);
			if (user == null) {
				throw new RuntimeException("没有【"+ email +"】邮箱的用户，无法重置!");
			}
			
			//2.验证邮箱验证码是否正确
			String code = resetPasswrodParam.getCode();
			Email checkEmail = new Email();
			checkEmail.setCode(code);
			checkEmail.setEmail(email);
			checkEmail.setToken(resetPasswrodParam.getToken());
			checkEmail.setType(Constants.EMAIL_TYPE_RESET);
			boolean isCheck = emailService.checkEmailCode(checkEmail);
			if (!isCheck) {
				throw new RuntimeException("邮箱验证码不正确");
			}
			
			//3.重置密码
			String password = resetPasswrodParam.getPassword();
			String decryptText = new String(RSAUtils.decryptByPrivateKey(password.getBytes()));
			password = SecurityUtils.encryptMD5(decryptText);
			user.setPassword(password);
			userService.updateUser(user);
			
			//4.修改邮件状态
			emailService.updateEmailState(checkEmail);
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}

	@Override
	public void sendEmail(Email email) {
		//发送邮件
		int res = emailService.sendEmail(email);
		if (res == Constants.NO_DATA) {
			throw new RuntimeException("发送邮件失败");
		}
	}
	
}
