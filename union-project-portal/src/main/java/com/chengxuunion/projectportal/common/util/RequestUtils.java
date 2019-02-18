package com.chengxuunion.projectportal.common.util;

import com.chengxuunion.projectportal.common.constant.Constants;
import com.chengxuunion.util.string.StringUtils;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-22 09:00
 * @Modified By:
 */
public class RequestUtils {

    private RequestUtils() {

    }

    /**
     * 判断是否ajax请求
     *
     * @param request
     * @return  是ajax请求返回true，否则返回false
     */
    public static boolean isAjax(HttpServletRequest request) {
        String requestedWith = request.getHeader("X-Requested-With");
        if (requestedWith != null && StringUtils.isEquals("XMLHttpRequest", requestedWith)) {
            return true;
        }

        return false;
    }
    
    /**
     * 请求的地址是否为公开地址
     *
     * @param publicUrl	公开地址列表
     * @param requestUrl	请求地址
     * @return
     */
    public static boolean isPublicUrl(String publicUrl, String requestUrl) {
        String[] publicUrlArr = publicUrl.split(Constants.SPLIT_STR);
        boolean isPublic = false;
        for (String url : publicUrlArr) {
            Pattern pattern = Pattern.compile(url);
            if (pattern.matcher(requestUrl).matches()) {
                isPublic = true;
                break;
            }
        }

        return isPublic;
    }
}
