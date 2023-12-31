package com.dandelion.use.server.service.user.repository.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dandelion.use.server.core.exception.GlobalException;
import com.dandelion.use.server.service.user.repository.entity.SysUser;
import com.dandelion.use.server.service.user.repository.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author lx6x
 * @since 2023/10/26
 */
@Service
public class SysUserDao extends ServiceImpl<SysUserMapper, SysUser> {

    /**
     * 根据用户名查询用户信息
     *
     * @param userName 用户名/登录账号
     * @return SysUser
     */
    public SysUser getByUserName(String userName) {
        return this.baseMapper.getUserRoleInfo(userName);
    }

    /**
     * 添加用户 - 不带权限信息
     *
     * @param sysUser 用户
     */
    public boolean addUser(SysUser sysUser) throws GlobalException {
        SysUser byUserName = getByUserName(sysUser.getUserName());
        if (null != byUserName) {
            throw new GlobalException("用户名重复");
        }
        return save(sysUser);
    }
}
