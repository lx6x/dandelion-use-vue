package com.dandelion.use.server.common.interceptor.impl;

import com.alibaba.fastjson2.JSON;
import com.dandelion.use.server.common.annotation.RepeatSubmit;
import com.dandelion.use.server.common.constant.RedisConstant;
import com.dandelion.use.server.common.interceptor.RepeatSubmitInterceptor;
import com.dandelion.use.server.common.properties.TokenCustomProperties;
import com.dandelion.use.server.common.utils.RedisUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 判断请求url和数据是否和上一次相同，如果和上次相同，则是重复提交表单。 有效时间为10秒内。
 *
 * @author L
 * @version 1.0
 * @date 2022/06/15 10:54
 */
@EnableConfigurationProperties(TokenCustomProperties.class)
@Component
public class SameUrlDataInterceptor extends RepeatSubmitInterceptor {

    public final String REPEAT_PARAMS = "repeatParams";

    public final String REPEAT_TIME = "repeatTime";

    private final RedisUtil redisUtil;

    private final TokenCustomProperties tokenCustomProperties;

    public SameUrlDataInterceptor(RedisUtil redisUtil, TokenCustomProperties tokenCustomProperties) {
        this.redisUtil = redisUtil;
        this.tokenCustomProperties = tokenCustomProperties;
    }

    @Override
    public boolean isRepeatSubmit(HttpServletRequest request, RepeatSubmit annotation) {
        String nowParams = "";


        // body参数为空，获取Parameter的数据
        if (StringUtils.isEmpty(nowParams)) {
            nowParams = JSON.toJSONString(request.getParameterMap());
        }

        Map<String, Object> nowMap = new HashMap<>(16);
        nowMap.put(REPEAT_PARAMS, nowParams);
        nowMap.put(REPEAT_TIME, System.currentTimeMillis());

        // 获取请求头中数据做为key
        String submitKey = StringUtils.trimToEmpty(request.getHeader(tokenCustomProperties.getHeader()));

        // 请求地址（作为存放cache的key值）
        String url = request.getRequestURI();

        // 实际 redis 获取key
        String repeatKey = RedisConstant.REPEAT_SUBMIT_KEY + url + submitKey;
        Object sessionObj = redisUtil.get(repeatKey);
        if (!ObjectUtils.isEmpty(sessionObj)) {
            Map<String, Object> sessionMap = (Map<String, Object>) sessionObj;
            if (sessionMap.containsKey(url)) {
                Map<String, Object> paramMap = (Map<String, Object>) sessionMap.get(url);
                if (compareParams(nowMap, paramMap) && compareTime(nowMap, paramMap, annotation.interval())) {
                    return true;
                }
            }
        }
        Map<String, Object> cacheMap = new HashMap<String, Object>();
        cacheMap.put(url, nowMap);
        redisUtil.set(repeatKey, cacheMap, annotation.interval(), TimeUnit.MILLISECONDS);
        return false;
    }


    /**
     * 判断参数是否相同
     */
    private boolean compareParams(Map<String, Object> nowMap, Map<String, Object> preMap) {
        String nowParams = (String) nowMap.get(REPEAT_PARAMS);
        String preParams = (String) preMap.get(REPEAT_PARAMS);
        return nowParams.equals(preParams);
    }

    /**
     * 判断两次间隔时间
     */
    private boolean compareTime(Map<String, Object> nowMap, Map<String, Object> preMap, int interval) {
        long time1 = (Long) nowMap.get(REPEAT_TIME);
        long time2 = (Long) preMap.get(REPEAT_TIME);
        if ((time1 - time2) < interval) {
            return true;
        }
        return false;
    }
}
