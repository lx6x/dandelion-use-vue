package com.dandelion.use.server.web.controller.system.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 登录请求
 *
 * @author lx6x
 * @date 2023/10/27
 */
@Getter
@Setter
@Schema(name = "登录请求")
public class LoginRequest {

    @Schema(name = "用户名")
    private String userName;

    @Schema(name = "密码")
    private String password;
}
