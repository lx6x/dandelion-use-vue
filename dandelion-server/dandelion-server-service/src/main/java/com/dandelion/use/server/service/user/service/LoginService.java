package com.dandelion.use.server.service.user.service;

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
}
