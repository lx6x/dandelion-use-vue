package com.dandelion.use.server.web.controller.system.converter;

import com.dandelion.use.server.service.user.repository.entity.SysUser;
import com.dandelion.use.server.web.controller.system.request.UserRegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author liujunfei
 * @date 2023/10/31
 */
@Mapper(componentModel = "spring")
public interface LoginConverter {

    LoginConverter INSTANCE = Mappers.getMapper(LoginConverter.class);

    SysUser register2SysUser(UserRegisterRequest request);
}
