package com.dandelion.use.server.web.controller.system;

import com.dandelion.use.server.core.result.R;
import com.dandelion.use.server.service.user.repository.entity.SysUser;
import com.dandelion.use.server.service.user.service.LoginService;
import com.dandelion.use.server.web.controller.system.converter.LoginConverter;
import com.dandelion.use.server.web.controller.system.request.LoginRequest;
import com.dandelion.use.server.web.controller.system.request.UserRegisterRequest;
import com.dandelion.use.server.web.controller.system.vo.LoginVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录
 *
 * @author lx6x
 * @date 2023/10/24
 */
@Tag(name = "登录登出", description = "用户登录登出")
@RestController
@RequestMapping("/api")
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 登录
     *
     * @param loginRequest 用户名
     */

    @Operation(summary = "登录")
    @PostMapping("/login")
    public R<LoginVo> login(@RequestBody LoginRequest loginRequest) {
        String userName = loginRequest.getUserName();
        String password = loginRequest.getPassword();
        String token = loginService.login(userName, password);
        return R.success(LoginVo.builder().token(token).build());
    }

    /**
     * 登出
     *
     * @return .
     */
    @Operation(summary = "登出")
    @PostMapping("/logout")
    public R<Boolean> logout() {
        return R.success("登出成功", loginService.logout());
    }

    /**
     * 注册
     */
    @Operation(summary = "注册")
    @PostMapping("/register")
    public R<Boolean> register(@RequestBody UserRegisterRequest request) {
        SysUser sysUser = LoginConverter.INSTANCE.register2SysUser(request);
        return R.success(loginService.register(sysUser));
    }

    /**
     * 刷新令牌
     */
    @Operation(summary = "刷新令牌")
    @PostMapping("/refresh")
    public R<String> refresh() {
        return R.success();
    }
}
