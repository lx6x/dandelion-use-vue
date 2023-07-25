package com.dandelion.use.server.common.core.model;


import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 登录信息
 *
 * @author L
 * @version 1.0
 * @date 2022/06/21 10:28
 */
@Schema(description = "登录信息")
public class LoginInfo {

    /**
     * 用户信息
     */
    @Schema(name = "用户信息")
    private String userName;
    /**
     * 密码
     */
    @Schema(name = "密码")
    private String password;
    /**
     * 验证码
     */
    @Schema(name = "验证码")
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
