package com.shuyu.spring.template.module.user.service;

import com.shuyu.spring.template.module.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shuyu.spring.template.module.user.entity.UserInfo;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author shuyu
 * @since 2019-01-15
 */
public interface IUserService extends IService<User> {

    User getByAccount(String account);

    UserInfo getUserInfo(String account);
}
