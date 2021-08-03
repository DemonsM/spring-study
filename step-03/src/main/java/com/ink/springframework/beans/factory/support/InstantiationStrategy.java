package com.ink.springframework.beans.factory.support;

import com.ink.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author MT
 * @date 2021/8/3 14:10
 */
public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> constructor, Object[] args);
}
