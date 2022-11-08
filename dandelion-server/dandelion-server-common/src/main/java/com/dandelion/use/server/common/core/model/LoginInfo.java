package com.dandelion.use.server.common.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * TODO 登录信息
 *
 * @author L
 * @version 1.0
 * @date 2022/06/21 10:28
 */
@ApiModel(description = "登录信息")
public class LoginInfo {

    /**
     * 用户信息
     */
    @ApiModelProperty(value = "用户信息",name = "userName",required = true)
    private String userName;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码",name = "password",required = true)
    private String password;
    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码",name = "code",required = false)
    private String code;
    /**
     * 唯一标识
     */
    private String uuid;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
