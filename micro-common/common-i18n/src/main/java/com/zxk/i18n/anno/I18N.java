package com.zxk.i18n.anno;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface I18N {
    boolean i18N() default true;
}