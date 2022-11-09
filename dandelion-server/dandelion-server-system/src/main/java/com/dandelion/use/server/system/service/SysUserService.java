package com.dandelion.use.server.system.service;

import com.dandelion.use.server.common.core.R;
import com.dandelion.use.server.common.core.entity.SysUser;

/**
 * TODO 用户
 *
 * @author L
 * @version 1.0
 * @date 2022/06/20 10:25
 */
public interface SysUserService {

    /**
     * 根据名称获取用户信息
     *
     * @param userName 用户名称
     * @return 用户
     * @author L
     */
    SysUser getUserByName(String userName);

    /**
     * 根据手机获取用户信息
     *
     * @param phone 手机号
     * @return 用户
     * @author L
     */
    SysUser getUserByPhone(String phone);

    /**
     * 根据邮箱获取用户信息
     *
     * @param email 邮箱
     * @return 用户
     * @author L
     */
    SysUser getUserByEmail(String email);

    /**
     * 注册
     *
     * @param sysUser 用户信息
     * @return 执行成功数
     * @author L
     */
    R<Integer> register(SysUser sysUser);
}
