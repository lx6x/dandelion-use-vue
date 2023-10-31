package com.dandelion.use.server.service.user.service;

import com.dandelion.use.server.service.user.repository.entity.SysUser;

/**
 * 登录
 *
 * @author lx6x
 * @date 2023/10/24
 */
public interface LoginService {

    /**
     * 登录功能
     *
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 登出
     */
    boolean logout();

    /**
     * 注册
     *
     * @param sysUser 用户信息
     */
    Boolean register(SysUser sysUser);


}
