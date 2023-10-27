package com.dandelion.use.server.web.controller.system;

import com.dandelion.use.server.core.result.R;
import com.dandelion.use.server.service.user.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录
 *
 * @author lx6x
 * @date 2023/10/24
 */
@Tag(name = "登录", description = "用户登录")
@RestController
@RequestMapping("/sys/login")
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 登录
     *
     * @param userName 用户名
     * @return .
     */
    @Operation(summary = "登录")
    @PostMapping("/{userName}")
    public R<String> login(@Parameter(description = "用户名") @PathVariable("userName") String userName) {


        return R.success();
    }
}
