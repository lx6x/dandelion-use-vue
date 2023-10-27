package com.dandelion.use.server.service.user.service.impl.security;

import com.dandelion.use.server.service.user.repository.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return this.sysUser.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
