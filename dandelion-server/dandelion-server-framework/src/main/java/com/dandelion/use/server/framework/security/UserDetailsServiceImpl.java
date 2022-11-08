package com.dandelion.use.server.framework.security;

import com.dandelion.use.server.common.core.entity.SysUser;
import com.dandelion.use.server.common.core.model.SysUserDetails;
import com.dandelion.use.server.common.enums.UserStatus;
import com.dandelion.use.server.common.exception.GlobalException;
import com.dandelion.use.server.system.service.SysUserService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * TODO 用户验证处理实现
 *
 * @author L
 * @version 1.0
 * @date 2022/06/17 14:59
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserService.getUserByName(username);
        if (ObjectUtils.isEmpty(user)) {
            throw new GlobalException("登录用户 [" + username + "] 不存在");
        }
        if (UserStatus.DISABLE.getCode() == Integer.parseInt(user.getStatus())) {
            throw new GlobalException("当前账号 [" + username + "] 已被停用");
        } else if (UserStatus.DELETE.getCode() == Integer.parseInt(user.getDelFlag())) {
            throw new GlobalException("当前账号 [" + username + "] 已被删除");
        }
        return new SysUserDetails(user);
    }
}
