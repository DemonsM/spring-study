package com.ink.springframework.beans.factory.config;

/**
 * @author MT
 * @date 2021/8/2 17:09
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
}
