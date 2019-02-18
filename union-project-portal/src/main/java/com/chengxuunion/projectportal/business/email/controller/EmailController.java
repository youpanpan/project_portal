package com.chengxuunion.projectportal.business.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chengxuunion.projectportal.business.email.model.Email;
import com.chengxuunion.projectportal.business.email.service.EmailService;
import com.chengxuunion.projectportal.common.constant.Constants;
import com.chengxuunion.projectportal.common.controller.BaseController;


/**
 * 邮件控制器
 *
 * @author youpanpan
 * @date 2018年8月30日
 * @version V1.0
 */
@Controller
@RequestMapping("/email")
public class EmailController extends BaseController {

	/**
	 * 邮件服务
	 */
	@Autowired
	private EmailService emailService;
	
	/**
	 * 发送邮件
	 * 
	 * @param email
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/sendemail")
	public Object sendEmail(@RequestBody Email email)  {
		
		int result = emailService.sendEmail(email);
		if (result == Constants.NO_DATA) {
			throw new RuntimeException("发送邮件失败，请稍后再试！");
		}
		
		return "";
	}
	
}
