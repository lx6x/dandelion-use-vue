package com.dandelion.use.server.web.controller.listener;

import com.dandelion.use.server.common.notify.NotifyMsgEvent;
import com.dandelion.use.server.common.notify.NotifyMsgTypeEnum;
import com.dandelion.use.server.common.utils.SpringUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 监听测试
 *
 * @author lx6x
 * @date 2023/10/20
 */
@Tag(name = "监听测试")
@RestController
@RequestMapping("/listener")
public class ListenerController {

    /**
     * 监听测试一
     */
    @Operation(summary = "监听测试一")
    @GetMapping("/login")
    public void login() {
        SpringUtil.publishEvent(new NotifyMsgEvent<>(this, NotifyMsgTypeEnum.LOGIN, NotifyMsgTypeEnum.LOGIN.getMsg()));
    }

    /**
     * 监听测试二
     */
    @Operation(summary = "监听测试二")
    @GetMapping("/register")
    public void register() {
        SpringUtil.publishEvent(new NotifyMsgEvent<>(this, NotifyMsgTypeEnum.REGISTER, NotifyMsgTypeEnum.REGISTER.getMsg()));

    }
}
