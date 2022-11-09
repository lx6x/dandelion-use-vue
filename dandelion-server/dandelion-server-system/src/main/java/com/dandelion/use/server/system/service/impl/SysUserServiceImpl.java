package com.dandelion.use.server.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.dandelion.use.server.common.core.R;
import com.dandelion.use.server.common.core.entity.SysUser;
import com.dandelion.use.server.common.utils.SecurityUtils;
import com.dandelion.use.server.system.mapper.SysUserMapper;
import com.dandelion.use.server.system.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public SysUser getUserByPhone(String phone) {
        return sysUserMapper.selectUserByPhone(phone);
    }

    @Override
    public SysUser getUserByEmail(String email) {
        return sysUserMapper.selectUserByEmail(email);
    }

    @Override
    public R<Integer> register(SysUser sysUser) {
        String userName = sysUser.getUserName();
        String phone = sysUser.getPhone();
        String email = sysUser.getEmail();
        if (!StringUtils.isEmpty(userName) && !ObjectUtil.isEmpty(getUserByName(userName))) {
            return R.fail("用户名重复");
        } else if (!StringUtils.isEmpty(phone) && !ObjectUtil.isEmpty(getUserByPhone(phone))) {
            return R.fail("手机号重复");
        } else if (!StringUtils.isEmpty(email) && !ObjectUtil.isEmpty(getUserByEmail(email))) {
            return R.fail("邮箱重复");
        }
        // 密码加密
        String password = sysUser.getPassword();
        if (StringUtils.isEmpty(password)) {
            return R.fail("密码不能为空");
        }
        sysUser.setPassword(SecurityUtils.encryptPassword(password));
        return R.success(sysUserMapper.insertSelective(sysUser));
    }
}
