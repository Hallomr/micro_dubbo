package com.zxk.provider.service.impl;

import com.zxk.example.service.ExampleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

@Slf4j
@DubboService
public class ExampleServiceImpl implements ExampleService {
    @Override
    public void exampleApi(String value) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("exampleApi value:{}",value);
    }
}
