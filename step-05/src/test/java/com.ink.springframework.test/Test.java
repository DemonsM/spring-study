package com.ink.springframework.test;

import cn.hutool.core.io.IoUtil;
import com.ink.springframework.beans.PropertyValue;
import com.ink.springframework.beans.PropertyValues;
import com.ink.springframework.beans.factory.config.BeanDefinition;
import com.ink.springframework.beans.factory.config.BeanReference;
import com.ink.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.ink.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.ink.springframework.core.io.DefaultResourceLoader;
import com.ink.springframework.core.io.Resource;
import com.ink.springframework.test.bean.UserDao;
import com.ink.springframework.test.bean.UserService;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author MT
 * @date 2021/8/3 11:10
 */
public class Test {

    private DefaultResourceLoader resourceLoader;

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

    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @org.junit.Test
    public void test_classpath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @org.junit.Test
    public void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @org.junit.Test
    public void test_url() throws IOException {
        Resource resource = resourceLoader.getResource("https://github.com/DemonsM/spring-study/blob/master/.gitignore");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @org.junit.Test
    public void test_xml() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        UserService userService = beanFactory.getBean("userService", UserService.class);
        String res = userService.queryUserInfo();
        System.out.println("测试结果：" + res);
    }

}
