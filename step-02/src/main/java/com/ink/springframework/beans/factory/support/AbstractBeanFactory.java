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
        Object singleton = getSingleton(beanName);
        if (Objects.nonNull(singleton)) {
            return singleton;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition);
}
