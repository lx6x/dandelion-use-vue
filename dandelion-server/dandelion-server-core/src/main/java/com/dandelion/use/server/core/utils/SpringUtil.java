package com.dandelion.use.server.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * spring 工具类
 *
 * @author lx6x
 * @date 2023/10/20
 */
@Component
public class SpringUtil implements ApplicationContextAware, EnvironmentAware {

    private volatile static ApplicationContext context;
    private volatile static Environment environment;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.context = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        SpringUtil.environment = environment;
    }

    /**
     * 发布事件消息
     *
     * @param event 事件
     */
    public static void publishEvent(ApplicationEvent event) {
        context.publishEvent(event);
    }
}
