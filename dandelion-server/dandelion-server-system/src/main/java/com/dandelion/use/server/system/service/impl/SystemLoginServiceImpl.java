package com.dandelion.use.server.system.service.impl;

import com.dandelion.use.server.common.core.model.SysUserDetails;
import com.dandelion.use.server.common.utils.TokenUtils;
import com.dandelion.use.server.system.service.SystemLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * TODO 登录实现
 *
 * @author L
 * @version 1.0
 * @date 2022/06/21 14:46
 */
@Service
public class SystemLoginServiceImpl implements SystemLoginService {

    @Autowired
    private TokenUtils tokenUtils;

    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public String login(String username, String password) {
        // 该方法会调用
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        SysUserDetails sysUserDetails = (SysUserDetails) authenticate.getPrincipal();
        return tokenUtils.generateToken(sysUserDetails);
    }
}
