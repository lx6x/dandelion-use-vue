package com.dandelion.use.server.web.controller.system.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户注册
 *
 * @author liujunfei
 * @date 2023/10/31
 */
@Getter
@Setter
@Schema(description = "用户注册")
public class UserRegisterRequest {

    @Schema(description = "用户注册")
    private String userName;

    @Schema(description = "用户注册")
    private String password;
}
