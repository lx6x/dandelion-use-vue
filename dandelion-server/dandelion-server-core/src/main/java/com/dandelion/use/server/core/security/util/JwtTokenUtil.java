package com.dandelion.use.server.core.security.util;

import com.dandelion.use.server.core.security.properties.TokenCustomProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken生成的工具类
 *
 * @author lx6x
 * @date 2023/10/25
 */
@Component
public class JwtTokenUtil {

    private static final String CLAIM_KEY_USERNAME = "sub_user_name";
    private static final String CLAIM_KEY_CREATED = "sub_created";
    private static final String CLAIM_KEY_USERID = "sub_user_id";

    @Resource
    private TokenCustomProperties tokenCustomProperties;

    /**
     * 根据负责生成JWT的token
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, tokenCustomProperties.getSecret())
                .compact();
    }

    /**
     * 从token中获取JWT中的负载
     */
    private Claims getClaimsFromToken(String token) throws Exception {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(tokenCustomProperties.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new RuntimeException("JWT验证失败");
        }
        return claims;
    }

    /**
     * 生成token的过期时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + tokenCustomProperties.getExpireTime() * 1000);
    }

    /**
     * 从token中获取登录用户名
     */
    public String getUserNameFromToken(String token) throws Exception {
        Claims claims = getClaimsFromToken(token);
        return claims.get(CLAIM_KEY_USERNAME, String.class);
    }

    /**
     * 验证token是否还有效
     *
     * @param token       客户端传入的token
     * @param userDetails 从数据库中查询出来的用户信息
     */
    public boolean validateToken(String token, UserDetails userDetails) throws Exception {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 判断token是否已经失效
     */
    public boolean isTokenExpired(String token) throws Exception {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) throws Exception {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 根据用户信息生成token
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(16);
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 判断token是否可以被刷新
     */
    public boolean canRefresh(String token) throws Exception {
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     */
    public String refreshToken(String token) throws Exception {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 获取指定负载
     *
     * @param token  .
     * @param key    键值
     * @param tClass 返回类型
     * @param <T>    .
     */
    public <T> T get(String token, String key, Class<T> tClass) throws Exception {
        Claims claims = getClaimsFromToken(token);
        return claims.get(key, tClass);
    }
}
