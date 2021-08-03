package com.ink.springframework.test;

import com.ink.springframework.beans.factory.config.BeanDefinition;
import com.ink.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.ink.springframework.test.bean.UserService;

/**
 * @author MT
 * @date 2021/8/3 11:10
 */
public class Test {

    @org.junit.Test
    public void test_BeanFactory() {
        //初始化BeanFactory
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        //注册Bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        defaultListableBeanFactory.registerBeanDefinition("userService", beanDefinition);

        //第一次获取Bean
        UserService userService = (UserService) defaultListableBeanFactory.getBean("userService");
        userService.getUserInfo();

        //第二次获取Bean
        UserService userService_singleton = (UserService) defaultListableBeanFactory.getBean("userService1");
        userService_singleton.getUserInfo();
    }
}
