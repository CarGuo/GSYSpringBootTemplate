package com.shuyu.spring.template.module.user.mapper;

import com.shuyu.spring.template.module.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shuyu.spring.template.module.user.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author shuyu
 * @since 2019-01-15
 */
public interface UserMapper extends BaseMapper<User> {

    User getByAccount(@Param("account") String account);

    UserInfo getUserInfo(@Param("account") String account);
}
