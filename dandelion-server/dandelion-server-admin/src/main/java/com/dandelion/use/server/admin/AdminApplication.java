package com.dandelion.use.server.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TODO start
 *
 * @author L
 * @version 1.0
 * @date 2022/06/14 16:31
 */
@SpringBootApplication(scanBasePackages = {"com.dandelion.use.server"})
@MapperScan(basePackages = {"com.dandelion.use.server.system.mapper"})
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
