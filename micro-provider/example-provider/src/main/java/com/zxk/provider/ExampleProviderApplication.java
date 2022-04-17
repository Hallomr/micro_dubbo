package com.zxk.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.zxk")
public class ExampleProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExampleProviderApplication.class,args);
    }
}
