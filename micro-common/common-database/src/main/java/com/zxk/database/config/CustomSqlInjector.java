package com.zxk.database.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;

import java.util.List;
/**
 * mybatisplus自定义批量操作
 * */
public class CustomSqlInjector extends DefaultSqlInjector {

 @Override
 public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
     List<AbstractMethod> methodList = super.getMethodList(mapperClass);
     methodList.add(new InsertBatchSomeColumn());
     return methodList;
 }
}