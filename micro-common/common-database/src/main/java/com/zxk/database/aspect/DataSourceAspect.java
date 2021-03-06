package com.zxk.database.aspect;

import com.zxk.database.anno.DataSource;
import com.zxk.database.multiple.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(-1)
@Slf4j
public class DataSourceAspect {
    //@within是可以通过aop拦截类上的注解,而@annotation是通过aop拦截方法上的注解
    @Pointcut("@within(com.zxk.database.anno.DataSource) || @annotation(com.zxk.database.anno.DataSource)")
    public void pointCut(){

    }

    @Before("pointCut() && @annotation(dataSource)")
    public void doBefore(DataSource dataSource){
        log.info("选择数据源---"+dataSource.value().getValue());
        DataSourceContextHolder.setDataSource(dataSource.value().getValue());
    }

    @After("pointCut()")
    public void doAfter(){
        DataSourceContextHolder.clear();
    }
}