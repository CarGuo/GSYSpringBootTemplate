package com.shuyu.spring.template.core.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * token
 */
public class JWTToken implements AuthenticationToken {
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
