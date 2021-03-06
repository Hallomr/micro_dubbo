package com.zxk.core.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class ThreadPoolUtils {
    private static final int cpuCount = Runtime.getRuntime().availableProcessors();
    private static final int corePoolSize = cpuCount + 1;
    private static final int maxPoolSize = cpuCount * 2 + 1;
    private static final long keepAliveTime = 30;
    private static ThreadPoolExecutor threadPoolExecutor;

    private static ThreadPoolExecutor getThreadPool() {
        if (threadPoolExecutor != null) {
            return threadPoolExecutor;
        } else {
            synchronized (ThreadPoolUtils.class) {
                if (threadPoolExecutor == null) {
                    log.info("corePoolSize:{},maxPoolSize:{}",corePoolSize,maxPoolSize);
                    threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS,
                            new LinkedBlockingQueue<>(32), new ThreadPoolExecutor.CallerRunsPolicy());
                }
                return threadPoolExecutor;
            }
        }
    }

    public  static void execute(Runnable runnable){
        getThreadPool().execute(runnable);
    }

    public  static <T> Future<T> submit(Callable<T> callable){
        return getThreadPool().submit(callable);
    }

    public static void shutdown() {
        getThreadPool().shutdown();
    }
}
