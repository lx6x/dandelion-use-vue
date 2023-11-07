package com.dandelion.use.server.service.user.service.impl.security;

import com.dandelion.use.server.service.user.repository.entity.SysRole;
import com.dandelion.use.server.service.user.repository.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author liujunfei
 * @date 2023/10/27
 */
public class UserDetailImpl implements UserDetails {

    private final SysUser sysUser;


    public UserDetailImpl(SysUser sysUser) {
        if (null == sysUser) {
            throw new UsernameNotFoundException("用户不存在");
        }
        this.sysUser = sysUser;
    }

    /**
     * 将应用程序用户的权限返回成一个GrantedAuthority实例集合
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SysRole> roles = this.sysUser.getRoles();
        return roles.stream().map(map -> new GrantedAuthorityImpl("ROLE_".concat(map.getRoleName()))).collect(Collectors.toList());
    }

    /**
     * 密码
     */
    @Override
    public String getPassword() {
        return this.sysUser.getPassword();
    }

    /**
     * 用户名
     */
    @Override
    public String getUsername() {
        return this.sysUser.getUserName();
    }

    /**
     * 账号是否在有效期内
     *
     * @return true-有效 false-无效
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 锁定
     *
     * @return true-未锁定 false-锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 凭据(密码)是否在有效期内
     *
     * @return true-有效 false-无效
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 启用禁用
     *
     * @return true-启用 false-禁用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

}
