package com.zxk.provider.service.impl;

import com.zxk.i18n.message.I18nMessages;
import com.zxk.i18n.utils.TraceUtils;
import com.zxk.example.service.ExampleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Locale;

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

    @Override
    public void exampleRpcContext(String value) {
        Object trace = TraceUtils.getTraceByClazz(Locale.class);
        String message = I18nMessages.getLocaleMessage("lan", (Locale) trace);
        log.info("exampleRpcContext value:{},trace:{},currentThread:{}",value,message,Thread.currentThread().getName());
    }
}