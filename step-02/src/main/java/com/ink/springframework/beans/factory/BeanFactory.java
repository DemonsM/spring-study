package com.ink.springframework.beans.factory;

/**
 * @author MT
 * @date 2021/8/2 17:15
 */
public interface BeanFactory {
    Object getBean(String beanName);
}
