package com.dandelion.use.server.admin.controller.redis;

import com.dandelion.use.server.common.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * TODO redis 测试controller
 *
 * @author L
 * @version 1.0
 * @date 2022-11-07 20:35
 */
@Api(value = "Redis 测试controller", tags = "Redis 测试controller", protocols = "http")
@RestController
@RequestMapping("/redis")
public class RedisController {

    private final RedisUtils redisUtils;

    public RedisController(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    /**
     * redis get 测试
     *
     * @param s key
     * @return value
     * @author L
     */
    @ApiOperation(value = "redis get 测试", notes = "Redis")
    @GetMapping("/get/{s}")
    public String get(@PathVariable("s") String s) {
        return redisUtils.get(s).toString();
    }

    /**
     * redis add 测试
     *
     * @param s 添加key/value
     * @return true-成功/false-失败
     * @author L
     */
    @ApiOperation(value = "redis add 测试", notes = "Redis")
    @PostMapping("/add/{s}")
    public Boolean add(@PathVariable("s") String s) {
        return redisUtils.set(s, s);
    }
}
