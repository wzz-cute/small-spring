package com.springframework.wzz.beans.factory.support;


import com.springframework.wzz.BeansException;
import com.springframework.wzz.beans.factory.BeanFactory;
import com.springframework.wzz.beans.factory.config.BeanDefinition;
import org.springframework.util.ObjectUtils;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String name) throws BeansException {
        Object singleton = getSingleton(name);
        if (!ObjectUtils.isEmpty(singleton)){
            return singleton;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name,beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String name) throws BeansException;

    protected abstract Object createBean(String name,BeanDefinition beanDefinition) throws BeansException;
}
