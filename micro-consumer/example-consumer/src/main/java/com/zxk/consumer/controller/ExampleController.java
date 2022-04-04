package com.zxk.consumer.controller;

import com.zxk.example.service.ExampleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/example")
@RefreshScope
@Slf4j
public class ExampleController {
    @Value("${nacos.config}")
    private String config;

    @DubboReference
    private ExampleService exampleService;

    @GetMapping(value = "/config")
    public void config(){
        log.info("example config:{}",config);
    }

    @GetMapping(value = "/dubbo")
    public void dubbo(){
        exampleService.exampleApi("dubbo");
    }
}
