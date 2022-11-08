package com.dandelion.gwt.domino.ui.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author L-jf
 */
@EnableDiscoveryClient
@EnableFeignClients
@RefreshScope
@SpringBootApplication
public class DominoUiApplication {
    public static void main(String[] args) {
        SpringApplication.run(DominoUiApplication.class, args);
    }
}
