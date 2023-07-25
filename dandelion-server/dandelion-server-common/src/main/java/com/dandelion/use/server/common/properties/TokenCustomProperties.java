package com.dandelion.use.server.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * token
 *
 * @author L
 * @version 1.0
 * @date 2022/06/15 17:26
 */
@ConfigurationProperties(prefix = "token")
public class TokenCustomProperties {

    /**
     * 自定义请求头
     */
    private String header;

    /**
     * token 密钥
     */
    private String secret;

    /**
     * 过期时间(ms)
     */
    private Integer expireTime;

    public Integer getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Integer expireTime) {
        this.expireTime = expireTime;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
