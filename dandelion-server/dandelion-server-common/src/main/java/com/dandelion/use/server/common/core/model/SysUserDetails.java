package com.dandelion.use.server.common.core.model;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dandelion.use.server.common.core.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * TODO UserDetails 实现
 *
 * @author L
 * @version 1.0
 * @date 2022/06/17 15:06
 */
public class SysUserDetails implements UserDetails {

    private static final long serialVersionUID = 5902692596623585714L;
    // 用户信息
    private SysUser sysUser;


    public SysUserDetails(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUserName();
    }

    /**
     * 账户是否未过期,过期无法验证
     *
     * @return false-过期（User account has expired）/ true-正常
     */
    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     *
     * @return false-锁定（User account is locked）/ true-正常
     */
    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     *
     * @return false-过期（User credentials have expired）/ true-正常
     */
    @JSONField(serialize = false)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     *
     * @return false-禁用（User is disabled）/ true-正常
     */
    @JSONField(serialize = false)
    @Override
    public boolean isEnabled() {
        return true;
    }

    public SysUser getSysUser() {
        return sysUser;
    }
}
