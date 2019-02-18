package com.chengxuunion.projectportal.common.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.chengxuunion.projectportal.common.constant.Constants;
import com.chengxuunion.projectportal.common.util.RequestUtils;
import com.chengxuunion.projectportal.common.util.SessionUtils;
import com.chengxuunion.util.string.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author youpanpan
 * @Description:    登录拦截器
 * @Date:创建时间: 2019-01-21 15:34
 * @Modified By:
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Value("${public.url}")
    private String publicUrl;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	// 请求地址
        String requestUrl = request.getRequestURI();
        String contextPath = request.getContextPath();
        if (StringUtils.isNotEmpty(contextPath)) {
            requestUrl = requestUrl.replace(contextPath, "");
        }
        if (RequestUtils.isPublicUrl(publicUrl, requestUrl)) {
            return true;
        }
    	
    	Object value = SessionUtils.getValue(Constants.USER);

        // 用户没有登录，则跳转到登录页
        if (value == null) {
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
