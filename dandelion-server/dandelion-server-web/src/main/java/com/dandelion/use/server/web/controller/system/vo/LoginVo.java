package com.dandelion.use.server.web.controller.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 登录返回
 *
 * @author lx6x
 * @date 2023/10/27
 */
@Getter
@Setter
@Builder
@Schema(description = "登录返回")
public class LoginVo {

    @Schema(description = "token")
    private String token;
}
