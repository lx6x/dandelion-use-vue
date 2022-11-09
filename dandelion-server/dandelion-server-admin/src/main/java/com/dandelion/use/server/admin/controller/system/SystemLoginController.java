package com.dandelion.use.server.admin.controller.system;

import com.dandelion.use.server.common.annotation.RepeatSubmit;
import com.dandelion.use.server.common.core.R;
import com.dandelion.use.server.common.core.model.LoginInfo;
import com.dandelion.use.server.system.service.SystemLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * TODO 系统登录验证
 *
 * @author L
 * @version 1.0
 * @date 2022/06/15 10:05
 */
@Api(value = "系统登录验证API", tags = "系统登录验证API", protocols = "http")
@RestController
public class SystemLoginController {

    @Resource
    private SystemLoginService systemLoginService;


    /**
     * 登录方法
     *
     * @param loginInfo 登录信息
     * @return void
     * @author L
     */
    @RepeatSubmit
    @ApiOperation(value = "登录方法", notes = "用户登录方法")
    @PostMapping("/login")
    public R<String> login(@RequestBody LoginInfo loginInfo) {
        return R.success(systemLoginService.login(loginInfo.getUserName(),loginInfo.getPassword()));
    }
}
