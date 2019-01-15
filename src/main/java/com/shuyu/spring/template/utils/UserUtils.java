package com.shuyu.spring.template.utils;

import com.shuyu.spring.template.config.GlobalConfig;
import com.shuyu.spring.template.module.user.entity.User;
import com.shuyu.spring.template.module.user.entity.UserRole;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

public class UserUtils {

    public static User createWxTmpUser(String openId) {
        User user = new User();
        user.setAccount(openId);
        user.setCreatetime(LocalDateTime.now());
        user.setPassword(DigestUtils.md5DigestAsHex(GlobalConfig.DEFAULT_PW.getBytes()));
        user.setDeptid(-1);
        user.setEmail("");
        user.setPhone("");
        user.setParentid("");
        user.setRoleid(UserRole.USER);
        user.setSex(0);
        user.setStatus(1);
        user.setVersion(0);
        user.setSalt("");
        user.setName("guest" + openId);
        user.setAvatar("");
        return user;
    }

}
