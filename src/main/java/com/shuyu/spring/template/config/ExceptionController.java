package com.shuyu.spring.template.config;

import com.shuyu.spring.template.utils.ResponseUtil;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理
 **/
@ControllerAdvice
@ResponseBody
public class ExceptionController {

    //未授权，没权限
    @ExceptionHandler(AuthorizationException.class)
    public Object authorizationExceptionHandler(AuthorizationException ex) {
        return ResponseUtil.fail(401,  "未获取权限");
    }
}