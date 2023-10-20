package com.dandelion.use.server.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * start
 *
 * @author L
 * @version 1.0
 * @date 2022/06/14 16:31
 */
@MapperScan("com.dandelion.use.server.service.**.mapper")
@SpringBootApplication(scanBasePackages = "com.dandelion.use.server")
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
