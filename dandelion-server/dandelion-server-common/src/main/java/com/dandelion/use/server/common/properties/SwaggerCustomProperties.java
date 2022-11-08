package com.dandelion.use.server.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TODO 自定义 Swagger 参数信息
 *
 * @author L
 * @version 1.0
 * @date 2022/06/14 17:12
 */
@ConfigurationProperties(prefix = "swagger")
public class SwaggerCustomProperties {

    /**
     * 描述
     */
    private String description;

    /**
     * 标题
     */
    private String title;

    /**
     * 是否开启 swagger
     */
    private Boolean enabled;

    /**
     * 请求前缀
     */
    private String pathMapping;

    public String getPathMapping() {
        return pathMapping;
    }

    public void setPathMapping(String pathMapping) {
        this.pathMapping = pathMapping;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
