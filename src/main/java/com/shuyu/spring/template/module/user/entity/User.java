package com.shuyu.spring.template.module.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shuyu.spring.template.module.role.entity.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author shuyu
 * @since 2019-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * md5密码盐
     */
    private String salt;

    private String name;

    /**
     * 性别（1：男 2：女）
     */
    private Integer sex;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 角色id
     */
    private Integer roleid;

    /**
     * 上级用户id
     */
    private String parentid;

    /**
     * 部门id
     */
    private Integer deptid;

    /**
     * 状态(1：启用  2：冻结  3：删除）
     */
    private Integer status;

    /**
     * 是否被删除
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;

    /**
     * 保留字段
     */
    private Integer version;

    /**
     * 验证码
     */
    @TableField(exist = false)//表示该属性不为数据库表字段，但又是必须使用的。
    private List<UserRole> roles;
}
