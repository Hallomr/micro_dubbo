package com.zxk.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    /**
     * 通过字节码获取
     *
     * @param beanClass
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }

    /**
     * 通过BeanName获取
     *
     * @param beanName
     * @param <T>
     * @return
     */
    public static <T> T getBean(String beanName) {
        return (T) context.getBean(beanName);
    }

    /**
     * 通过beanName和字节码获取
     *
     * @param name
     * @param beanClass
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> beanClass) {
        return context.getBean(name, beanClass);
    }

    public void register(String beanName,Object beanObject){
        if(StringUtils.isBlank(beanName)){
            AnnotatedGenericBeanDefinition definition = new AnnotatedGenericBeanDefinition(beanObject.getClass());
            beanName = new AnnotationBeanNameGenerator().generateBeanName(definition, null);
        }
        ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext)getApplicationContext();
        if(applicationContext.containsBean(beanName)){
            return;
        }

        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        beanFactory.registerSingleton(beanName,beanObject);
    }
}