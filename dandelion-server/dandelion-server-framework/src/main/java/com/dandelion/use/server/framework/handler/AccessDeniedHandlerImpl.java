package com.dandelion.use.server.framework.handler;

import com.alibaba.fastjson2.JSON;
import com.dandelion.use.server.common.core.R;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO 当访问接口没有权限时，自定义的返回结果
 *
 * @author L
 * @version 1.0
 * @date 2022/06/16 17:43
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSON.toJSONString(R.forbidden(e.getMessage())));
        response.getWriter().flush();
    }
}
