package com.dandelion.use.server.core.security;

import com.dandelion.use.server.core.security.filter.JwtAuthenticationTokenFilter;
import com.dandelion.use.server.core.properties.SecurityProperties;
import com.dandelion.use.server.core.properties.TokenCustomProperties;
import com.dandelion.use.server.core.utils.JwtTokenUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author lx6x
 * @date 2023/10/24
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final SecurityProperties securityProperties;

    private final TokenCustomProperties tokenCustomProperties;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserDetailsService userDetailsService;

    public SecurityConfig(SecurityProperties securityProperties, TokenCustomProperties tokenCustomProperties,
                          JwtTokenUtil jwtTokenUtil, UserDetailsService userDetailsService) {
        this.securityProperties = securityProperties;
        this.tokenCustomProperties = tokenCustomProperties;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }


    /**
     * 安全过滤器使用 SecurityFilterChain API 插入到 FilterChainProxy 中。
     * 这些过滤器可用于许多不同的目的，例如身份验证、授权、漏洞利用保护等。 筛选器按特定顺序执行，以确保在正确的时间调用它们，例如，应在执行授权之前调用执行身份验证的 。
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 默认登录表单验证
//                .formLogin(withDefaults())
                // 使用 Jwt
                .csrf(AbstractHttpConfigurer::disable)
                // 对请求验证
                .authorizeHttpRequests(registry -> {
                    registry
                            // 添加排除项
                            .requestMatchers(securityProperties.getExcludes()).permitAll()
                            // 所有请求都要拦截验证，除了登录成功的除外
                            .anyRequest().authenticated();
                })
                .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public JwtAuthenticationTokenFilter authFilter() throws Exception {
        return new JwtAuthenticationTokenFilter(tokenCustomProperties, jwtTokenUtil, userDetailsService);
    }
}
