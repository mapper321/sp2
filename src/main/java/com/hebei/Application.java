package com.hebei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author: mapper
 * @since: 2020/3/31
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient/*(autoRegister = false)*/
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
