package com.dandelion.use.server.core.security.filter;

import com.dandelion.use.server.core.properties.TokenCustomProperties;
import com.dandelion.use.server.core.utils.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 在用户名和密码校验前添加的过滤器，如果请求中有jwt的token且有效，会取出token中的用户名，然后调用SpringSecurity的API进行登录操作
 *
 * @author lx6x
 * @date 2023/10/25
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    private final TokenCustomProperties tokenCustomProperties;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserDetailsService userDetailsService;

    public JwtAuthenticationTokenFilter(TokenCustomProperties tokenCustomProperties, JwtTokenUtil jwtTokenUtil, UserDetailsService userDetailsService) {
        this.tokenCustomProperties = tokenCustomProperties;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,@NonNull FilterChain chain) throws ServletException, IOException {

        String prefix = this.tokenCustomProperties.getPrefix();
        // 获取自定义请求头
        String authHeader = request.getHeader(this.tokenCustomProperties.getHeader());
        if (StringUtils.isNotBlank(authHeader) && authHeader.startsWith(prefix)) {
            // 截取实际token
            String authToken = authHeader.substring(prefix.length());

            // 根据token获取登录名
            String username = jwtTokenUtil.getUserNameFromToken(authToken);
            logger.info("当前请求 username: {}", username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    logger.info("authenticated user:{}", username);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);

    }
}
