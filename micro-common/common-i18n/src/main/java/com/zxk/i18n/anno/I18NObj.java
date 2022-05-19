package com.zxk.i18n.anno;

import java.lang.annotation.*;
/**
 * 标记对象数据
 * */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface I18NObj {
    boolean i18NObj() default true;
}