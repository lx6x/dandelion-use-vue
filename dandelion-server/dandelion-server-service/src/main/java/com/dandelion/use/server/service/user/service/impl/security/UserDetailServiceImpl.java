package com.dandelion.use.server.service.user.service.impl.security;

import com.dandelion.use.server.service.user.repository.dao.SysUserDao;
import com.dandelion.use.server.service.user.repository.entity.SysUser;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author lx6x
 * @date 2023/10/26
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private SysUserDao sysUserDao;

    @Override
    public UserDetailImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserDao.getByUserName(username);
        return new UserDetailImpl(sysUser);
    }

}
