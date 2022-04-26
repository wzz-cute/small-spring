package com.springframework.wzz.beans.factory.support;

import com.springframework.wzz.BeansException;
import com.springframework.wzz.beans.factory.config.BeanDefinition;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    protected Object createBean(String name, BeanDefinition beanDefinition) throws BeansException {
        Object bean = null;
        try {
            bean = beanDefinition.getBean().newInstance();
        }catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(name,bean);
        return bean;
    }
}
