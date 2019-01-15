package com.shuyu.spring.template.module.role.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author shuyu
 * @since 2019-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 序号
     */
    private Integer roleid;

    /**
     * 父角色id
     */
    private Integer pid;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 部门名称
     */
    private Integer deptid;

    /**
     * 用户权限
     */
    private String permission;

    /**
     * 提示
     */
    private String tips;

    /**
     * 保留字段(暂时没用）
     */
    private Integer version;


    /**
     * 管理员
     */
    public static final int ADMIN = 1;
    /**
     * 广告商
     */
    public static final int ADVERT = 2;
    /**
     * 代理商
     */
    public static final int PROXY = 3;
    /**
     * 普通用户
     */
    public static final int USER = 10;


    public static String getPermission(int roleid) {
        switch (roleid) {
            case ADMIN:
                return "admin";
            case ADVERT:
                return "advert";
            case PROXY:
                return "proxy";
            default:
                return "guest";
        }
    }

}
