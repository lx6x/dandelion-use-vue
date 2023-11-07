package com.dandelion.use.server.service.user.service.impl.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author lx6x
 * @date 2023/11/7
 */
public class GrantedAuthorityImpl implements GrantedAuthority {

    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    @Override
    public String toString() {
        return "GrantedAuthorityImpl{" +
                "authority='" + authority + '\'' +
                '}';
    }
}
