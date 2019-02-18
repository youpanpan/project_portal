package com.chengxuunion.projectportal.common.controller;

import javax.servlet.http.HttpServletRequest;

import com.chengxuunion.projectportal.common.constant.Constants;
import com.chengxuunion.util.id.UUIDUtils;
import com.chengxuunion.util.string.StringUtils;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-19 16:58
 * @Modified By:
 */
public abstract class BaseController {
	
	/**
	 * 往session中设置session
	 * 
	 * @param request
	 * @return	token值
	 */
	public String setToken(HttpServletRequest request) {
		String token = UUIDUtils.getId();
		request.getSession().setAttribute(Constants.TOKEN, token);
		
		return token;
	}
	
	/**
	 * 验证token
	 * 
	 * @param request
	 * @param token
	 * @return
	 */
	public boolean checkToken(HttpServletRequest request, String token) {
		if (StringUtils.isEquals(token, (String)request.getSession().getAttribute(Constants.TOKEN))) {
			return true;
		}
		
		return false;
	}
}
