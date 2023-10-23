package com.dandelion.use.server.web.controller.redis;

import com.dandelion.use.server.common.utils.RedisUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


/**
 * redis
 *
 * @author lx6x
 * @date 2022/11/07
 */
@Tag(name = "redis", description = "redis操作,可供测试使用")
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private RedisUtil redisUtil;


    /**
     * get
     *
     * @param key key
     */
    @Operation(summary = "get")
    @GetMapping("/get/{key}")
    public String get(@Parameter(description = "key值") @PathVariable("key") String key) {
        return redisUtil.get(key).toString();
    }

    /**
     * set
     *
     * @param key 添加key/value
     */
    @Operation(
            summary = "set",
            parameters = {@Parameter(name = "key", description = "key值"), @Parameter(name = "value", description = "value值")}
    )
    @PostMapping("/set/{key}/{value}")
    public Boolean set(@PathVariable("key") String key, @PathVariable("value") String value) {
        return redisUtil.set(key, value);
    }
}
