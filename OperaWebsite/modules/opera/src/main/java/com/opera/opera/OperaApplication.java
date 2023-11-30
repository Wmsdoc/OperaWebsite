package com.opera.opera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 戏曲模块
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients({"com.opera.system.api"})
public class OperaApplication {

    public static void main(String[] args) {
        SpringApplication.run(OperaApplication.class, args);
        System.out.println("-------------------------OperaApplication started.---------------------------");
    }
}
