package com.dandelion.use.server.web.controller.redis;

import com.dandelion.use.server.common.utils.RedisUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * redis
 *
 * @author L
 * @version 1.0
 * @date 2022-11-07 20:35
 */
@Tag(name = "Redis")
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
    @Operation(description = "redis get 测试")
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
    @Operation(description = "redis add 测试")
    @PostMapping("/add/{s}")
    public Boolean add(@PathVariable("s") String s) {
        return redisUtils.set(s, s);
    }
}
