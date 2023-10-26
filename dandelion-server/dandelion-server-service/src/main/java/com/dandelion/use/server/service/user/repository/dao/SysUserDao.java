package com.dandelion.use.server.service.user.repository.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
