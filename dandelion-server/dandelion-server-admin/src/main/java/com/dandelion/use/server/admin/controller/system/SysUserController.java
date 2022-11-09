package com.dandelion.use.server.admin.controller.system;

import com.dandelion.use.server.common.core.R;
import com.dandelion.use.server.common.core.entity.SysUser;
import com.dandelion.use.server.common.utils.SecurityUtils;
import com.dandelion.use.server.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TODO
 *
 * @author L
 * @version 1.0
 * @date 2022-11-09
 */
@RestController
@RequestMapping("/sys/user")
@Api(value = "系统用户", tags = "系统用户")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public R<Integer> register(@RequestBody SysUser sysUser) {
        return sysUserService.register(sysUser);
    }

    @GetMapping("/getLoginUser")
    @ApiOperation("获取当前登录用户信息")
    public R<SysUser> user() {
        return R.success(SecurityUtils.getLoginUser());
    }
}
