package com.shuyu.spring.template.core.shiro;

import org.apache.shiro.authz.UnauthorizedException;
import org.json.JSONObject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * preHandle -> isAccessAllowed -> isLoginAttempt -> executeLogin
 */
public class ManagementFilter extends JWTFilter {

    private final ManagementProperties managementProperties;

    public ManagementFilter(ManagementProperties managementProperties) {
        this.managementProperties = managementProperties;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws UnauthorizedException {
        return false;
    }

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        return true;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        // 读取请求内容
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            //将json字符串转换为json对象
            JSONObject json = new JSONObject(sb.toString());
            String username = json.getString("username");
            String password = json.getString("password");
            if (managementProperties.getUsername().equals(username) && managementProperties.getPassword().equals(password)) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new UnauthorizedException();
        }
        return false;
    }
}
