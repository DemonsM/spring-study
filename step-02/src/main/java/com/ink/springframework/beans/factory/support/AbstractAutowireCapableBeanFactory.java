package com.ink.springframework.beans.factory.support;

import com.ink.springframework.beans.BeansException;
import com.ink.springframework.beans.factory.config.BeanDefinition;

/**
 * @author MT
 * @date 2021/8/2 17:24
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        Object bean;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }

}
