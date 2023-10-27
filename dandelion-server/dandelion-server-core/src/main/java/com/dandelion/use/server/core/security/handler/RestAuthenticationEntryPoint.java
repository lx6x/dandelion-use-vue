package com.dandelion.use.server.core.security.handler;

import cn.hutool.json.JSONUtil;
import com.dandelion.use.server.core.result.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 当未登录或者token失效访问接口时，自定义的返回结果
 *
 * @author lx6x
 * @date 2023/10/27
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(R.fail(authException.getMessage())));
        response.getWriter().flush();
    }
}
