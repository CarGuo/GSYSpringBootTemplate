package com.shuyu.spring.template.config;

import com.shuyu.spring.template.utils.ResponseUtil;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.stream.Collectors;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handle(MethodArgumentNotValidException exception) {
        //获取参数校验错误集合
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        //格式化以提供友好的错误提示
        String data = String.format("参数校验错误（%s）：%s", fieldErrors.size(),
                fieldErrors.stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.joining(";")));
        //参数校验失败响应失败个数及原因
        return  ResponseUtil.badArgument(data);
    }
}