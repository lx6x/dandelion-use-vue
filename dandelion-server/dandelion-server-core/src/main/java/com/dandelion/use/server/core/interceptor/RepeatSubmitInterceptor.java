package com.dandelion.use.server.core.interceptor;

import com.alibaba.fastjson2.JSON;
import com.dandelion.use.server.core.annotation.RepeatSubmit;
import com.dandelion.use.server.core.result.R;
import com.dandelion.use.server.core.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
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
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        if (handler instanceof HandlerMethod handlerMethod) {
            Method method = handlerMethod.getMethod();
            RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);
            if (annotation != null) {
                if (this.isRepeatSubmit(request, annotation)) {
                    R<String> fail = R.fail(annotation.message());
                    ServletUtil.renderString(response, JSON.toJSONString(fail));
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
