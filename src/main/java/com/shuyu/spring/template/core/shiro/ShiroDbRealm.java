package com.shuyu.spring.template.core.shiro;

import com.shuyu.spring.template.module.role.entity.UserRole;
import com.shuyu.spring.template.module.user.entity.User;
import com.shuyu.spring.template.module.user.entity.UserStatus;
import com.shuyu.spring.template.module.user.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 */
@Component
public class ShiroDbRealm extends AuthorizingRealm {
    private final IUserService userService;

    @Autowired
    public ShiroDbRealm(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 必须重写此方法，不然会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //System.out.println("————身份认证方法————");
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (username == null || !JWTUtil.verify(token, username)) {
            throw new AuthenticationException("token认证失败!请重新登录。");
        }
        User currentUser = userService.getUserInfo(username);
        if (currentUser == null) {
            throw new AuthenticationException("该用户不存在！");
        }
        int ban = currentUser.getStatus();
        if (ban != UserStatus.NORMAL) {
            throw new AuthenticationException("该用户已被封号！");
        }
        return new SimpleAuthenticationInfo(currentUser, token, "ShiroDbRealm");
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User currentUser = (User)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roleSet = new HashSet<>();
        Set<String> permissionSet = new HashSet<>();
        //获得该用户角色
        List<UserRole> roles = currentUser.getRoles();
        if (roles != null && !roles.isEmpty()) {
            for (UserRole userRole: roles) {
                //设置该用户拥有的角色和权限
                roleSet.add(userRole.getPermission());
                //每个角色拥有默认的权限
                //每个用户可以设置新的权限
                permissionSet.add(userRole.getPermission());
            }

        }
        info.setRoles(roleSet);
        info.setStringPermissions(permissionSet);
        return info;
    }
}
