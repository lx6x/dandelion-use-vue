package com.dandelion.use.server.common.notify;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

/**
 * 消息事件
 *
 * @author lx6x
 * @date 2023/10/20
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class NotifyMsgEvent<T> extends ApplicationEvent {

    private T content;
    private NotifyMsgTypeEnum typeEnum;

    public NotifyMsgEvent(Object source, NotifyMsgTypeEnum typeEnum, T content) {
        super(source);
        this.typeEnum = typeEnum;
        this.content = content;
    }
}
