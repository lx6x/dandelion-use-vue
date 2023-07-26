package com.dandelion.use.server.common.interceptor;

import com.alibaba.fastjson2.JSON;
import com.dandelion.use.server.common.annotation.RepeatSubmit;
import com.dandelion.use.server.common.R;
import com.dandelion.use.server.common.utils.ServletUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;

/**
 * 防止重复提交拦截器
 *
 * @author L
 * @version 1.0
 * @date 2022/06/15 10:36
 */
@Component
public abstract class RepeatSubmitInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);
            if (annotation != null) {
                if (this.isRepeatSubmit(request, annotation)) {
                    R<String> fail = R.fail(annotation.message());
                    ServletUtils.renderString(response, JSON.toJSONString(fail));
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 验证是否重复提交由子类实现具体的防重复提交的规则
     *
     * @param request    .
     * @param annotation .
     * @return boolean
     * @author L
     */
    public abstract boolean isRepeatSubmit(HttpServletRequest request, RepeatSubmit annotation);
}
