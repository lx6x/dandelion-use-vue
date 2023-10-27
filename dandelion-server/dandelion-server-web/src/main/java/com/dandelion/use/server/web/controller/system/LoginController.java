package com.dandelion.use.server.web.controller.system;

import com.dandelion.use.server.core.result.R;
import com.dandelion.use.server.service.user.service.LoginService;
import com.dandelion.use.server.web.controller.system.request.LoginRequest;
import com.dandelion.use.server.web.controller.system.vo.LoginVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

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
     * @param loginRequest 用户名
     * @return .
     */
    @Operation(summary = "登录")
    @PostMapping
    public R<LoginVo> login(@RequestBody LoginRequest loginRequest) {
        String userName = loginRequest.getUserName();
        String password = loginRequest.getPassword();
        String token = loginService.login(userName, password);
        return R.success(LoginVo.builder().token(token).build());
    }
}
