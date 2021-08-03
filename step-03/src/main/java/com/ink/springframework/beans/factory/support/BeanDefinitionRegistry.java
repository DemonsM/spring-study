package com.ink.springframework.beans.factory.support;

import com.ink.springframework.beans.factory.config.BeanDefinition;

/**
 * @author MT
 * @date 2021/8/3 11:02
 */
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
