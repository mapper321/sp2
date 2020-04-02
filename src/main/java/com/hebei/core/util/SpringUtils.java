package com.hebei.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author: mapper
 * @since: 2020/4/2
 */
@Component
public class SpringUtils implements ApplicationContextAware {


    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContextParam) throws BeansException {
        applicationContext = applicationContextParam;
    }


    public static Object getBean(String tClass) {
        return applicationContext.getBean(tClass);
    }

    public static <T> T getBean(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }
}
