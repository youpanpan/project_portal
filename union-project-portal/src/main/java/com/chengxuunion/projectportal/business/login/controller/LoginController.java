package com.chengxuunion.projectportal.business.login.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chengxuunion.component.oauth.common.BaseAuthInfo;
import com.chengxuunion.projectportal.business.email.model.Email;
import com.chengxuunion.projectportal.business.login.model.request.LoginParam;
import com.chengxuunion.projectportal.business.login.model.request.ResetPasswrodParam;
import com.chengxuunion.projectportal.business.login.service.LoginService;
import com.chengxuunion.projectportal.common.constant.Constants;
import com.chengxuunion.projectportal.common.controller.BaseController;

/**
 * @Author youpanpan
 * @Description:    TODO
 * @Date:创建时间: 2019年1月26日
 * @Modified By:
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	
	/**
	 * 登录服务
	 */
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private List<BaseAuthInfo> authInfos;

	/**
	 * 跳转到登录页面
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping
	public String toLoginPage(HttpServletRequest request) {
		String token = this.setToken(request);
		request.setAttribute(Constants.TOKEN, token);
		
		//添加OAuth地址
		request.setAttribute("auths", authInfos);
		
		return "login/login";
	}
	
	/**
	 * 跳转到忘记密码页面
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping("/forgotpassword")
	public String toForgotPasswordPage(HttpServletRequest request)  {
		String token = this.setToken(request);
		request.setAttribute(Constants.TOKEN, token);
		
		//添加OAuth地址
		request.setAttribute("auths", authInfos);
		
		return "login/forgotPassword";
		
	}
	
	/**
	 * 退出登录
	 * 
	 * @return
	 */
	@GetMapping("/do-logout")
	@ResponseBody
	public Object logout()  {
		loginService.logout();
        return "";
	}
	
	/**
	 * 登录
	 * 
	 * @param params
	 * @param request
	 * @return
	 */
	@ResponseBody
	@PostMapping("/do-login")
	public Object login(LoginParam loginParam, HttpServletRequest request)  {
		if (!this.checkToken(request, loginParam.getToken())) {
			throw new RuntimeException("会话已过期");
		}
		
		loginService.login(loginParam);
		
		return "";
	}
	
	
	/**
	 * 发送注册邮件
	 * 
	 * @param email
	 * @return
	 */
	@ResponseBody
	@PostMapping("/sendemail")
	public Object sendEmail(Email email, HttpServletRequest request)  {
		if (!this.checkToken(request, email.getToken())) {
			throw new RuntimeException("会话已过期");
		}
		
		loginService.sendEmail(email);
		
		return "";
	}
	
	/**
	 * 重置密码
	 * 
	 * @param params
	 * @return
	 */
	@ResponseBody
	@PostMapping("/resetpassword")
	public Object resetPassword(ResetPasswrodParam resetPasswordParam, HttpServletRequest request)  {
		if (!this.checkToken(request, resetPasswordParam.getToken())) {
			return new RuntimeException("会话已过期");
		}
		
		loginService.resetPassword(resetPasswordParam);
		
		return "";
	}

}
