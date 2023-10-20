package com.dandelion.use.server.service.notify.listener;

import com.dandelion.use.server.common.notify.NotifyMsgEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 消息监听
 *
 * @author lx6x
 * @date 2023/10/20
 */
@Async
@Service
public class NotifyMsgListener<T> implements ApplicationListener<NotifyMsgEvent<T>> {

    private static final Logger logger = LoggerFactory.getLogger(NotifyMsgListener.class);

    @Override
    public void onApplicationEvent(NotifyMsgEvent<T> event) {
        logger.info("--> 消息监听类型 {}",event.getTypeEnum());
    }
}
