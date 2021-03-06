package com.zxk.consumer.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * 业务异常熔断降级处理类
 * */
@Slf4j
public class Fallback {
    public static void dubboFallback(@RequestParam("dubbo") String dubbo){
        log.info("fallback dubbo:{}",dubbo);
    }
}
