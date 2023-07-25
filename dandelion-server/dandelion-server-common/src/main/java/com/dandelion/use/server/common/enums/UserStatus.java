package com.dandelion.use.server.common.enums;

/**
 * 用户状态
 *
 * @author L
 * @version 1.0
 * @date 2022/06/20 14:54
 */
public enum UserStatus {
    NORMAL(0, "正常"),
    DISABLE(1, "停用"),
    DELETE(2, "删除");

    private final int code;
    private final String info;

    UserStatus(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return code;
    }


    public String getInfo() {
        return info;
    }

}
