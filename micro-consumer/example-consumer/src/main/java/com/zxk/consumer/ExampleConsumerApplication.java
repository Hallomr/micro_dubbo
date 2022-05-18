package com.zxk.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ServletComponentScan
@ComponentScan(value = "com.zxk")
public class ExampleConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExampleConsumerApplication.class,args);
    }
}