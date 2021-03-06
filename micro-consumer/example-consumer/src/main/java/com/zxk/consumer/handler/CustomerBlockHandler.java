package com.zxk.consumer.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * sentinel 流控熔断降级处理类
 * */
@Slf4j
public class CustomerBlockHandler{
    public static void handlerException(@RequestParam("dubbo") String dubbo,BlockException exception) {
        log.info("自定义的限流降级异常处理");
    }
}
