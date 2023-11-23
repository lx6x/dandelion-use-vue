package com.dandelion.use.server.core.security.filter;

import com.dandelion.use.server.core.constant.RedisConstant;
import com.dandelion.use.server.core.utils.RedisUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 图形验证过滤器
 *
 * @author lx6x
 * @date 2023/11/23
 */
public class VerificationCodeFilter extends OncePerRequestFilter {

    private final RedisUtil redisUtil;

    public VerificationCodeFilter(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        // 判断当前请求是否为登录
        if ("/api/login".equals(requestURI)) {
            // 校验验证码是否正确
            String captcha = request.getParameter("captcha");
            if (StringUtils.isEmpty(captcha)) {
                throw new RuntimeException("验证码无效");
            }
            String captchaRedis = redisUtil.get(RedisConstant.captcha.concat(captcha)).toString();
            if (StringUtils.isEmpty(captchaRedis) || captchaRedis.equals(captcha)) {
                throw new RuntimeException("验证码无效");
            }
            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(request, response);
        }

    }
}
