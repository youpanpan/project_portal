package com.chengxuunion.projectportal.business.index.controller;

import com.chengxuunion.projectportal.common.constant.Constants;
import com.chengxuunion.projectportal.common.util.SessionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chengxuunion.projectportal.common.controller.BaseController;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 首页控制器
 *
 * @author youpanpan
 * @date 2019年1月22日
 * @version V1.0
 */
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {
	
	@GetMapping
	public String index() {
		return "index";
	}
	
	@GetMapping("/admin")
	public String adminIndex() {
		return "admin/index";
	}

	@GetMapping("/set-language/{language}")
	@ResponseBody
	public Object setLanguage(@PathVariable("language") String language, HttpServletRequest request) {
		SessionUtils.setValue(Constants.LANGUAGE, language);
		return "";
	}

}
