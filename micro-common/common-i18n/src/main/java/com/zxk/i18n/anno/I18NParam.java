package com.zxk.i18n.anno;

import java.lang.annotation.*;
/**
 * 标记基本类型数据及包装类、字符串等
 * */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface I18NParam {
    boolean i18NParam() default true;
}
