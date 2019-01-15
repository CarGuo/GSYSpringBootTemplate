package com.shuyu.spring.template.module.user.controller;


import com.shuyu.spring.template.annotation.LoginUser;
import com.shuyu.spring.template.module.user.entity.User;
import com.shuyu.spring.template.module.user.service.IUserService;
import com.shuyu.spring.template.utils.ResponseUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author shuyu
 * @since 2019-01-15
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public IUserService userService;

    @GetMapping("/info")
    public Object getInfo(@LoginUser User user) {
        if (user == null || user.getAccount() == null) {
            return ResponseUtil.fail(500, "用户信息异常");
        }
        return ResponseUtil.ok(user);
    }

    @GetMapping("/test/permission")
    @RequiresRoles({"admin"})
    public Object test(@LoginUser User user) {
        if (user == null || user.getAccount() == null) {
            return ResponseUtil.fail(500, "用户信息异常");
        }
        return ResponseUtil.ok(user);
    }

}

