package com.shuyu.spring.template.core.login;

import com.shuyu.spring.template.annotation.LoginUser;
import com.shuyu.spring.template.config.GlobalConfig;
import com.shuyu.spring.template.core.shiro.JWTUtil;
import com.shuyu.spring.template.module.user.entity.User;
import com.shuyu.spring.template.module.user.entity.UserStatus;
import com.shuyu.spring.template.module.user.service.IUserService;
import com.shuyu.spring.template.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    public IUserService userService;

    /**
     * 跳转到登录页面
     */
    @PostMapping("/login")
    public Object login(@RequestParam String username, @RequestParam String password) {

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ResponseUtil.badArgument();
        }

        User user = userService.getByAccount(username);

        if (user == null) {
            return ResponseUtil.fail(401, "账号密码不对");
        }

        if (!password.equals(user.getPassword())) {
            return ResponseUtil.fail(401, "账号密码不对");
        }

        if (user.getStatus() == UserStatus.FREEZEN) {
            return ResponseUtil.fail(401, "账号已冻结");
        }

        if (user.getStatus() == UserStatus.DELETE) {
            return ResponseUtil.fail(401, "账号已删除");
        }

        String token = JWTUtil.createToken(username);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        return ResponseUtil.ok(map);
    }

    /**
     * 跳转到登录页面
     */
    @PostMapping("/logout")
    public Object logout(@RequestHeader(value= GlobalConfig.LOGIN_TOKEN_KEY) String token) {
        if (token != null && !token.isEmpty()) {
            JWTUtil.disableToken(token);
            return ResponseUtil.ok();
        }
        return ResponseUtil.badArgument();
    }

    @PutMapping("/changepw")
    public Object logout(@LoginUser User user, @RequestHeader(value=GlobalConfig.LOGIN_TOKEN_KEY) String token, @RequestParam String oldPassword, @RequestParam String newPassword) {
        if (StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(oldPassword)) {
            return ResponseUtil.badArgument();
        }
        if (StringUtils.isEmpty(newPassword) || StringUtils.isEmpty(newPassword)) {
            return ResponseUtil.badArgument();
        }

        if (!oldPassword.equals(user.getPassword())) {
            return ResponseUtil.badArgument("旧密码输入不正确");
        }

        User db = userService.getByAccount(user.getAccount());
        db.setPassword(newPassword);
        userService.saveOrUpdate(db);

        JWTUtil.disableToken(token);

        return ResponseUtil.ok();
    }
}
