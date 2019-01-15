package com.shuyu.spring.template.module.user.entity;

public class UserRole {
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
