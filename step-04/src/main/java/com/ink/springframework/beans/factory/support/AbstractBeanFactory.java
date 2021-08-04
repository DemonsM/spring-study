package com.ink.springframework.beans.factory.support;

import com.ink.springframework.beans.factory.BeanFactory;
import com.ink.springframework.beans.factory.config.BeanDefinition;

import java.util.Objects;

/**
 * @author MT
 * @date 2021/8/2 17:16
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String beanName) {
        return doGetBean(beanName, null);
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return doGetBean(beanName, args);
    }

    protected <T> T doGetBean(String name, Object[] args) {
        Object bean = getSingleton(name);
        if (Objects.nonNull(bean)) {
            return (T) bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args);
}
