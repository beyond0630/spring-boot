package com.sweet.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringUitls implements ApplicationContextAware {

    private static ApplicationContext context = null;

    // 获取当前环境
    public static String getActiveProfile() {
        return context.getEnvironment().getActiveProfiles()[0];
    }

    // 传入线程中
    public static <T> T getBean(Class<T> beanName) {
        return context.getBean(beanName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
