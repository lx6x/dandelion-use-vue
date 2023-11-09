package com.dandelion.use.server.core.security;

import com.dandelion.use.server.core.security.filter.JwtAuthenticationTokenFilter;
import com.dandelion.use.server.core.security.handler.RestAuthenticationEntryPoint;
import com.dandelion.use.server.core.security.handler.RestfulAccessDeniedHandler;
import com.dandelion.use.server.core.security.properties.SecurityProperties;
import com.dandelion.use.server.core.security.properties.TokenCustomProperties;
import com.dandelion.use.server.core.security.util.JwtTokenUtil;
import com.dandelion.use.server.core.utils.RedisUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author lx6x
 * @date 2023/10/24
 */
@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final SecurityProperties securityProperties;

    private final TokenCustomProperties tokenCustomProperties;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserDetailsService userDetailsService;

    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    private final RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    private final RedisUtil redisUtil;

    public SecurityConfig(SecurityProperties securityProperties,
                          TokenCustomProperties tokenCustomProperties,
                          JwtTokenUtil jwtTokenUtil,
                          UserDetailsService userDetailsService,
                          RestAuthenticationEntryPoint restAuthenticationEntryPoint,
                          RestfulAccessDeniedHandler restfulAccessDeniedHandler,
                          RedisUtil redisUtil) {
        this.securityProperties = securityProperties;
        this.tokenCustomProperties = tokenCustomProperties;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.restfulAccessDeniedHandler = restfulAccessDeniedHandler;
        this.redisUtil = redisUtil;
    }

    /**
     * 安全过滤器使用 SecurityFilterChain API 插入到 FilterChainProxy 中。
     * 这些过滤器可用于许多不同的目的，例如身份验证、授权、漏洞利用保护等。 筛选器按特定顺序执行，以确保在正确的时间调用它们，例如，应在执行授权之前调用执行身份验证的 。
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> {
                    cors.configurationSource(corsConfigurationSource());
                })
                // 使用 Jwt,不使用 Session
                .csrf(AbstractHttpConfigurer::disable)
                // 对请求验证
                .authorizeHttpRequests(registry -> {
                    registry
                            // 请求白名单,可动态配置
                            .requestMatchers(securityProperties.getExcludes()).permitAll()
                            .requestMatchers("/api/login", "/api/register").permitAll()
                            // 所有请求都要拦截验证，除了登录成功的除外
                            .anyRequest().authenticated();
                })
                .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(except -> {
                    // 验证自定义返回
                    except.accessDeniedHandler(restfulAccessDeniedHandler).authenticationEntryPoint(restAuthenticationEntryPoint);
                })
        ;
        return http.build();
    }

    @Bean
    public JwtAuthenticationTokenFilter authFilter() throws Exception {
        return new JwtAuthenticationTokenFilter(tokenCustomProperties, jwtTokenUtil, userDetailsService, redisUtil);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * cors 配置
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        //允许所有域名进行跨域调用
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
