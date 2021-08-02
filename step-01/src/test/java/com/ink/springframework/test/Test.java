package com.ink.springframework.test;

import com.ink.springframework.BeanDefinition;
import com.ink.springframework.BeanFactory;
import com.ink.springframework.bean.UserService;

/**
 * @author MT
 * @date 2021/8/2 16:22
 */
public class Test {

    @org.junit.Test
    public void testBeanDefinition() {
        //初始化Bean工厂
        BeanFactory beanFactory = new BeanFactory();

        //注入Bean
        beanFactory.registerBeanDefinition("userService", new BeanDefinition(new UserService()));

        //获取Bean
        UserService userService = (UserService) beanFactory.getBean("userService");

        userService.getUserInfo();
    }
}
