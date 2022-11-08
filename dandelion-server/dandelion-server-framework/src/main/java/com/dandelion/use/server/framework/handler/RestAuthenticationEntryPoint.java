package com.dandelion.use.server.framework.handler;

import com.alibaba.fastjson2.JSON;
import com.dandelion.use.server.common.core.R;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO 当未登录或者token失效访问接口时，自定义的返回结果
 *
 * @author L
 * @version 1.0
 * @date 2022/06/16 17:44
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String result = JSON.toJSONString(R.unauthorized(authException.getMessage()));
        response.getWriter().println(result);
        response.getWriter().flush();
    }
}
