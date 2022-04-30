package com.zxk.provider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
/**
 * springboot定时任务多线程异步执行
 * */
@Configuration
@EnableAsync
public class ScheduleConfig {

    @Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(Runtime.getRuntime().availableProcessors() * 2 + 1);
        return taskScheduler;
    }
}