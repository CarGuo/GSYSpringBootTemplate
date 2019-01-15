package com.shuyu.spring.template.annotation.support;

import com.shuyu.spring.template.annotation.LoginUser;
import com.shuyu.spring.template.config.GlobalConfig;
import com.shuyu.spring.template.core.shiro.JWTUtil;
import com.shuyu.spring.template.module.user.entity.User;
import com.shuyu.spring.template.module.user.mapper.UserMapper;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 *
 */
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private UserMapper userMapper;

    public LoginUserHandlerMethodArgumentResolver(@NonNull UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(User.class) && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {

        String token = request.getHeader(GlobalConfig.LOGIN_TOKEN_KEY);
        if (token == null || token.isEmpty()) {
            return null;
        }
        String account = JWTUtil.getUsername(token);
        if (account == null || account.isEmpty()) {
            return null;
        }
        return userMapper.getByAccount(account);
    }
}
