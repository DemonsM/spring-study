package com.ink.springframework.test;

import com.ink.springframework.beans.PropertyValue;
import com.ink.springframework.beans.PropertyValues;
import com.ink.springframework.beans.factory.config.BeanDefinition;
import com.ink.springframework.beans.factory.config.BeanReference;
import com.ink.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.ink.springframework.test.bean.UserDao;
import com.ink.springframework.test.bean.UserService;

/**
 * @author MT
 * @date 2021/8/3 11:10
 */
public class Test {

    @org.junit.Test
    public void test_BeanFactory() {
        //初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //注册UserDao Bean
        BeanDefinition beanDefinition = new BeanDefinition(UserDao.class);
        beanFactory.registerBeanDefinition("userDao", beanDefinition);

        //UserService设置属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        //UserService注入Bean
        BeanDefinition userServiceBeanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", userServiceBeanDefinition);

        //第一次获取Bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.getUserInfo();

        //第二次获取Bean
        //UserService userService_singleton = (UserService) beanFactory.getBean("userService");
        //userService_singleton.getUserInfo();
    }
}
