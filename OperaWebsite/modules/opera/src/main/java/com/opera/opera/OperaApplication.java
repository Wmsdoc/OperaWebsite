package com.opera.opera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 戏曲模块
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients({"com.opera.api.*"})
@ComponentScan({"com.opera.*"})
public class OperaApplication {

    public static void main(String[] args) {
        SpringApplication.run(OperaApplication.class, args);
        System.out.println("-------------------------OperaApplication started.---------------------------");
    }
}
