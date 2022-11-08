package com.dandelion.use.server.system.service;

import com.dandelion.use.server.common.core.entity.SysUser;

/**
 * TODO 用户
 *
 * @author L
 * @version 1.0
 * @date 2022/06/20 10:25
 */
public interface SysUserService {

    SysUser getUserByName(String userName);
}
