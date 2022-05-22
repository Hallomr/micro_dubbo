package com.zxk.database.anno;

import java.lang.annotation.*;

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface MapperFilter {
    boolean isMapper() default true;
}
