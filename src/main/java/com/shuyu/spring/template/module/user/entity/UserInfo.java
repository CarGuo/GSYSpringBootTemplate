package com.shuyu.spring.template.module.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.util.List;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author shuyu
 * @since 2019-01-15
 */
@Data
public class UserInfo extends User {

    /**
     * 验证码
     */
    @TableField(exist = false)//表示该属性不为数据库表字段，但又是必须使用的。
    private List<UserRole> roles;

}
