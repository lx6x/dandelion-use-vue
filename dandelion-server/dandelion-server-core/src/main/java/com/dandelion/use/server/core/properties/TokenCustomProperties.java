package com.dandelion.use.server.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * token
 *
 * @author lx6x
 * @version 1.0
 * @date 2022/06/15
 */
@Getter
@Setter
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
}
