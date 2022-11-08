package com.dandelion.use.server.system.mapper;

import com.dandelion.use.server.common.core.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * TODO
 *
 * @author L
 * @version 1.0
 * @date 2022/06/20 10:53
 */
@Mapper
public interface SysUserMapper {

    int insertSelective(SysUser record);

    /**
     * 根据名称获取用户信息(待改)
     *
     * @param userName 用户名
     * @return com.dandelion.use.server.common.core.entity.SysUser
     * @author L
     */
    SysUser selectUserByName(String userName);
}
