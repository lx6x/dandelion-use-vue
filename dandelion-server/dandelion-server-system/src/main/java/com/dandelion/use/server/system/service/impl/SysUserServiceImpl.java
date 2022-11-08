package com.dandelion.use.server.system.service.impl;

import com.dandelion.use.server.common.core.entity.SysUser;
import com.dandelion.use.server.system.mapper.SysUserMapper;
import com.dandelion.use.server.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO 用户实现
 *
 * @author L
 * @version 1.0
 * @date 2022/06/20 10:25
 */
@Service
public class SysUserServiceImpl implements SysUserService {


    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public SysUser getUserByName(String userName) {
        return sysUserMapper.selectUserByName(userName);
    }
}
