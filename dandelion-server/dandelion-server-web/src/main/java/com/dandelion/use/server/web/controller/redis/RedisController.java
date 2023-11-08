package com.dandelion.use.server.web.controller.redis;

import com.dandelion.use.server.core.result.R;
import com.dandelion.use.server.core.utils.RedisUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * redis
 *
 * @author lx6x
 * @date 2022/11/07
 */
@Tag(name = "redis", description = "redis操作,可供测试使用")
@RestController
@PreAuthorize("hasRole('redis')")
@RequestMapping("/api/redis")
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
    public R<Object> get(@Parameter(description = "key值") @PathVariable("key") String key) {
        return R.success(redisUtil.get(key));
    }

    /**
     * set
     *
     * @param map 添加key/value
     */
    @Operation(summary = "set")
    @PostMapping("/set")
    public R<Boolean> set(@RequestBody Map<String, String> map) {
        return R.success(redisUtil.set(map.get("key"), map.get("value")));
    }

    /**
     * hasKey
     *
     * @param key key值
     */
    @Operation(summary = "hasKey")
    @GetMapping("/hasKey/{key}")
    public R<Boolean> hasKey(@Parameter(name = "key", description = "key值") @PathVariable("key") String key) {
        return R.success(redisUtil.hasKey(key));
    }
}
