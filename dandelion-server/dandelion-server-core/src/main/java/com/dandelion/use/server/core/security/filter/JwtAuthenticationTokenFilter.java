package com.dandelion.use.server.core.security.filter;

import com.dandelion.use.server.core.constant.RedisConstant;
import com.dandelion.use.server.core.security.properties.TokenCustomProperties;
import com.dandelion.use.server.core.security.util.JwtTokenUtil;
import com.dandelion.use.server.core.utils.RedisUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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

    private final RedisUtil redisUtil;

    public JwtAuthenticationTokenFilter(TokenCustomProperties tokenCustomProperties, JwtTokenUtil jwtTokenUtil, UserDetailsService userDetailsService, RedisUtil redisUtil) {
        this.tokenCustomProperties = tokenCustomProperties;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.redisUtil = redisUtil;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain) throws ServletException, IOException {

        String prefix = this.tokenCustomProperties.getPrefix().concat(" ");
        // 获取自定义请求头
        String authHeaderToken = request.getHeader(this.tokenCustomProperties.getHeader());
        if (StringUtils.isNotBlank(authHeaderToken) && authHeaderToken.startsWith(prefix)) {
            // 截取实际token
            String token = authHeaderToken.substring(prefix.length());

            try {
                // 根据token获取登录名
                String username = jwtTokenUtil.getUserNameFromToken(token);
                logger.info("当前请求 username: {}", username);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // 判断 token 是否过期 TODO 不知道这么写会有什么问题
                    String concat = RedisConstant.TOKEN.concat(username);
                    if (redisUtil.hasKey(concat)) {
                        // 从 redis 获取对应 token 用作比对
                        String redisToken = (String) redisUtil.get(concat);
                        // 一致放行，不一致已 redis 中为准
                        if (StringUtils.equals(token, redisToken)) {
                            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
                            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    } else {
                        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                        if (null != authentication) {
                            new SecurityContextLogoutHandler().logout(request, response, authentication);
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        chain.doFilter(request, response);
    }
}
