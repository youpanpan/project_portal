package com.chengxuunion.projectportal.business.register.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chengxuunion.component.oauth.common.BaseAuthInfo;
import com.chengxuunion.projectportal.business.email.model.Email;
import com.chengxuunion.projectportal.business.register.service.IRegisterService;
import com.chengxuunion.projectportal.common.constant.Constants;
import com.chengxuunion.projectportal.common.controller.BaseController;
import com.chengxuunion.util.collection.MapUtils;


/**
 * 注册控制器
 *
 * @author kutome
 * @date 2018年7月14日
 * @version V1.0
 */
@Controller
@RequestMapping("/register")
public class RegisterController extends BaseController {
	
	/**
	 * 注册服务
	 */
	@Autowired
	private IRegisterService registerService;
	
	@Autowired
	private List<BaseAuthInfo> authInfos;

	/**
	 * 跳转到注册页面
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping
	public String toRegisterPage(ModelMap model, HttpServletRequest request) {
		String token = this.setToken(request);
		request.setAttribute(Constants.TOKEN, token);
		
		//添加OAuth地址
		request.setAttribute("auths", authInfos);
		
		return "register/register";
	}
	
	/**
	 * 跳转到使用条款页面
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/useterms")
	public String toUseTermsPage(ModelMap model)  {
		return "register/useTerms"; 
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
		
		registerService.sendEmail(email);
		
		return "";
	}
	
	/**
	 * 注册用户
	 * 
	 * @param params
	 * @return
	 * @throws RegisterException
	 */
	@ResponseBody
	@PostMapping("/registeruser")
	public Object registerUser(@RequestBody Map<String, Object> params, HttpServletRequest request) {
		if (!this.checkToken(request, MapUtils.getString(params, Constants.TOKEN))) {
			throw new RuntimeException("会话已过期");
		}
		
		registerService.registerUser(params);
		
		return "";
	}

}
