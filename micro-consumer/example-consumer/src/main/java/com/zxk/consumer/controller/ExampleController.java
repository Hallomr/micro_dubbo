package com.zxk.consumer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zxk.consumer.fallback.Fallback;
import com.zxk.consumer.handler.CustomerBlockHandler;
import com.zxk.example.service.ExampleService;
import com.zxk.file.service.FileStore;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/example")
@RefreshScope
@Slf4j
public class ExampleController {
    @Value("${nacos.config}")
    private String config;

    @DubboReference
    private ExampleService exampleService;

    @Autowired
    private FileStore fileStore;

    @GetMapping(value = "/config")
    @ApiOperation(value = "读取nacos配置文件信息")
    public void config(HttpServletRequest request){
        String gateway_sign = request.getHeader("gateway_sign");
        log.info("access gateway:{}",gateway_sign);
        log.info("example config:{}",config);
    }

    //fallback：若本接口业务异常需熔断降级，则调用fallback指定的接口 ;
    // blockHandler：若调用被sentinel流控限流或服务降级，则调用blockHandler指定的接口
    @GetMapping(value = "/dubbo")
    @ApiOperation(value = "测试服务dubbo调用")
    @SentinelResource(value = "dubboApi",blockHandlerClass = CustomerBlockHandler.class,blockHandler = "handlerException",
            fallbackClass = Fallback.class,fallback = "dubboFallback")
    public void dubbo(@RequestParam("dubbo") String dubbo){
        int i = 1/0;//测试fallback熔断降级
        exampleService.exampleApi(dubbo);
    }

    @GetMapping(value = "/rpcContext")
    @ApiOperation(value = "公用参数透传")
    public void rpcContext(){
        log.info("currentThread:{}",Thread.currentThread().getName());
        exampleService.exampleRpcContext("dubbo1");
        exampleService.exampleRpcContext("dubbo2");
    }

    @PostMapping("/upload")
    @ApiOperation(value = "oss文件存储")
    public void upload(@RequestParam("file") MultipartFile file, String bucket) {
        try {
            fileStore.uploadFile(file,bucket);
        } catch (Exception e) {
            log.info("upload fail:{}",e.getMessage());
        }
    }
}