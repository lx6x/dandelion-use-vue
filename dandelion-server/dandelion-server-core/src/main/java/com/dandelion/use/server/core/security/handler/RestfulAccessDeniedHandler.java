package com.dandelion.use.server.core.security.handler;

import cn.hutool.json.JSONUtil;
import com.dandelion.use.server.core.result.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 当访问接口没有权限时，自定义的返回结果
 *
 * @author lx6x
 * @date 2023/10/27
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(R.fail(e.getMessage())));
        response.getWriter().flush();
    }

}
