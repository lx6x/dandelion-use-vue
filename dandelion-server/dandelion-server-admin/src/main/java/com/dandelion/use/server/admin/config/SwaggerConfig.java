package com.dandelion.use.server.admin.config;

import com.dandelion.use.server.common.properties.DandelionCustomProperties;
import com.dandelion.use.server.common.properties.SwaggerCustomProperties;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger接口配置
 *
 * @author L
 * @version 1.0
 * @date 2022/06/14 16:48
 */
@EnableKnife4j
@EnableSwagger2
@Configuration
@EnableConfigurationProperties({SwaggerCustomProperties.class, DandelionCustomProperties.class})
public class SwaggerConfig {

    @Autowired
    private SwaggerCustomProperties swaggerCustomProperties;

    @Autowired
    private DandelionCustomProperties dandelionCustomProperties;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
            // 设置展示信息
            .apiInfo(apiInfo())
            .enable(swaggerCustomProperties.getEnabled())
            // 设置那些接口暴露给 Swagger 展示
            .select()
            // 扫描所有使用注解的api
//            .apis(RequestHandlerSelectors.withMethodAnnotation(Api.class))
            // 指定包路径
            .apis(RequestHandlerSelectors.basePackage("com.dandelion.use.server"))
            .paths(PathSelectors.any())
            .build();
    }

    /**
     * 设置索要展示的信息
     *
     * @return springfox.documentation.service.ApiInfo
     * @author L
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            // 标题
            .title(swaggerCustomProperties.getTitle())
            // 描述
            .description(swaggerCustomProperties.getDescription())
            // 版本
            .version(dandelionCustomProperties.getVersion())
            // 作者信息
            .contact(new Contact(dandelionCustomProperties.getName(), dandelionCustomProperties.getUrl(), dandelionCustomProperties.getEmail()))
            .build();
    }
}
