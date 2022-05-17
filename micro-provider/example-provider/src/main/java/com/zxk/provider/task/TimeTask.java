package com.zxk.provider.task;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
/**
 * 测试redisson分布式锁
 * */
@Component
@EnableScheduling
@Slf4j
public class TimeTask {
    private static final String redisson_lock = "redisson_lock";

    @Autowired
    private RedissonClient redissonClient;

    @Scheduled(cron = "0 0/5 * * * ?")
    @Async
    public void execute() {
        RLock lock = redissonClient.getLock(redisson_lock);
        try {
            if(lock.tryLock(10, TimeUnit.SECONDS)){
                log.info("get redisson lock");
                Thread.sleep(60000);
            }else{
                log.info("not get redisson lock");
            }
        } catch (Exception e) {
            log.info("TimeTask exception:{}",e.getMessage());
        } finally {
            if(lock.isLocked()){
                //锁存在
                if(lock.isHeldByCurrentThread()){
                    //当前线程持有锁
                    lock.unlock();
                }
            }
        }
    }
}

