package com.dandelion.use.server.system.service;

import com.dandelion.use.server.common.core.entity.SysUser;

/**
 * TODO 登录
 *
 * @author L
 * @version 1.0
 * @date 2022/06/21 14:46
 */
public interface SystemLoginService {

    /**
     * 登录功能
     *
     * @param username 用户名
     * @param password 密码
     * @return SysUser
     */
    SysUser login(String username, String password);
}
