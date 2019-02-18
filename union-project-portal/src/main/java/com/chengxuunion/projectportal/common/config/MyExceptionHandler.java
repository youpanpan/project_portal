package com.chengxuunion.projectportal.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chengxuunion.projectportal.common.model.ErrorCode;
import com.chengxuunion.projectportal.common.model.ResultCode;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-08 09:56
 * @Modified By:
 */
@ControllerAdvice
public class MyExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultCode exceptionHandler(Exception ex) {
        logger.error("捕获到Exception异常",ex);

        return ResultCode.fail(ErrorCode.ERROR.getCode(), ex.getMessage(), null);
    }
}
