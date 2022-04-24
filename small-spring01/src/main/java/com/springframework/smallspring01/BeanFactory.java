package com.springframework.smallspring01;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 定义bean工厂
 */
public class BeanFactory {
    private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public Object getBean(String beanName){
        return this.beanDefinitionMap.get(beanName).getBean();
    }

    public void registerBeanDefinition(String name,BeanDefinition beanDefinition){
        this.beanDefinitionMap.put(name,beanDefinition);
    }

}
