package com.zxk.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.zxk")
public class ExampleOneProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExampleOneProviderApplication.class,args);
    }
}