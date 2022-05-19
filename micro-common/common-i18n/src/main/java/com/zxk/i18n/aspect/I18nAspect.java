package com.zxk.i18n.aspect;

import com.zxk.core.vo.Result;
import com.zxk.i18n.anno.I18NObj;
import com.zxk.i18n.anno.I18NParam;
import com.zxk.i18n.message.I18nMessages;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
/**
 * controller 国际化切面
 *
 * */
@Aspect
@Component
@Slf4j
public class I18nAspect {
    @AfterReturning(pointcut = "@annotation(com.zxk.i18n.anno.I18N))", returning = "result")
    public void doAfterReturningAdvice(JoinPoint joinPoint, Object result) {

        //处理Result对象
        if (result instanceof Result){
            //result msg内容国际化
            ((Result) result).setMsg(I18nMessages.getMessage(((Result) result).getMsg()));
            //result data 国际化
            Object data = ((Result) result).getData();
            recursiveObj(data);
        }

    }

    private void recursiveObj(Object data) {
        if (data != null && data.getClass().isAnnotationPresent(I18NObj.class)) {
            //对象
            try {
                for (Field declaredField: data.getClass().getDeclaredFields()) {
                    //基本类型字段
                    if (declaredField.isAnnotationPresent(I18NParam.class)) {
                        declaredField.setAccessible(true);
                        String messageUtf8 = I18nMessages.getMessage((String) declaredField.get(data));
                        if (messageUtf8 != null) {
                            declaredField.set(data, messageUtf8);
                        }
                        continue;
                    }
                    //对象类型字段
                    if (declaredField.isAnnotationPresent(I18NObj.class)) {
                        declaredField.setAccessible(true);
                        Object o = declaredField.get(data);
                        //处理list集合
                        boolean b = i18NList(o);
                        if (b) {
                            continue;
                        }
                        //处理对象
                        recursiveObj(o);
                    }
                }
            } catch (Exception e) {
                log.info("国际化切面处理异常：{}", e.getMessage());
            }
        } else {
            if (data == null) {
                return;
            }
            //处理list对象
            i18NList(data);
        }
    }

    /**
     * list元素为对象
     * */
    private boolean i18NList(Object result) {
        if (result instanceof List) {
            if (((List) result).size() > 0) {
                if(((List) result).get(0).getClass().isAnnotationPresent(I18NObj.class)){
                    try {
                        for (Object o : (List) result) {
                            recursiveObj(o);
                        }
                    } catch (Exception e) {
                        log.info("国际化切面处理异常：{}", e.getMessage());
                    }
                }else {
                    i18nBaseList(result);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * list元素为字符串
     * */
    private boolean i18nBaseList(Object result) {
        if (result instanceof List) {
            List list = ((List) result);
            if (list.size() > 0) {
                try {
                    for (int i = 0; i <= list.size()-1; i++) {
                        list.add(i, I18nMessages.getMessage((String) list.get(i)));
                    }
                } catch (Exception e) {
                    log.info("国际化切面处理异常：{}", e.getMessage());
                }

            }
            return true;
        }
        return false;
    }
}
