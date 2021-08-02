package com.ink.springframework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author MT
 * @date 2021/8/2 16:14
 */
public class BeanFactory {
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBean();
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }
}
