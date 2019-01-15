package com.shuyu.spring.template.module.role.service.impl;

import com.shuyu.spring.template.module.role.entity.UserRole;
import com.shuyu.spring.template.module.role.mapper.UserRoleMapper;
import com.shuyu.spring.template.module.role.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author shuyu
 * @since 2019-01-15
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
