package com.dandelion.use.server.service.user.service.impl.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * 表示授予身份验证对象的权限
 *
 * @author liujunfei
 * @date 2023/11/6
 */
public class GrantedAuthorityImpl implements GrantedAuthority {

    private final String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    @Override
    public String toString() {
        return "GrantedAuthorityImpl{" +
                "authority='" + authority + '\'' +
                '}';
    }
}
