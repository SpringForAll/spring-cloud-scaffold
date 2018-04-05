package com.spring4all.scaffold.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;
import org.springframework.cloud.netflix.turbine.stream.TurbineApplication;

/**
 * @author fangzhibin 
 */
@SpringBootApplication
@EnableTurbineStream
@EnableDiscoveryClient
public class TurbineStreamServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TurbineApplication.class, args);
    }
}
