package com.shuyu.spring.template.module.user.service.impl;

import com.shuyu.spring.template.module.user.entity.User;
import com.shuyu.spring.template.module.user.entity.UserInfo;
import com.shuyu.spring.template.module.user.mapper.UserMapper;
import com.shuyu.spring.template.module.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author shuyu
 * @since 2019-01-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User getByAccount(String account) {
        return baseMapper.getByAccount(account);
    }

    @Override
    public UserInfo getUserInfo(String account) {
        return baseMapper.getUserInfo(account);
    }
}
