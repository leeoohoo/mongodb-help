/*
 * @Description: 
 * @version: 
 * @Author: leeoohoo
 * @Date: 2020-04-24 16:27:35
 * @LastEditors: leeoohoo
 * @LastEditTime: 2020-04-26 10:24:42
 */
package com.learn.mongod.utils;

import org.springframework.context.ApplicationContext;


public class SpringUtil {
    private static ApplicationContext context = null;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    public static Object getBean(String name) {
        return context.getBean(name);
    }

    public static <T> T getBean(Class<T> requiredType) {
        return context.getBean(requiredType);
    }
}
