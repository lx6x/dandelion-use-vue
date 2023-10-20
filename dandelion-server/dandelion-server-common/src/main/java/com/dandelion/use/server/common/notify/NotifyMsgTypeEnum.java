package com.dandelion.use.server.common.notify;

import lombok.Getter;

/**
 * 消息事件类型
 *
 * @author lx6x
 * @date 2023/10/20
 */
@Getter
public enum NotifyMsgTypeEnum {
    LOGIN(1, "用户登录"),
    REGISTER(2, "用户注册");


    private final int type;

    private final String msg;

    NotifyMsgTypeEnum(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }
}
