package com.dandelion.use.server.system.service.impl;

import com.dandelion.use.server.system.service.SystemLoginService;
import org.springframework.stereotype.Service;

/**
 * 登录实现
 *
 * @author L
 * @version 1.0
 * @date 2022/06/21 14:46
 */
@Service
public class SystemLoginServiceImpl implements SystemLoginService {


    @Override
    public String login(String username, String password) {
        return "ok";
    }
}
