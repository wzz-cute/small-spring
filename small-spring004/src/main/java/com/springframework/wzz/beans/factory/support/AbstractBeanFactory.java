package com.springframework.wzz.beans.factory.support;


import com.springframework.wzz.BeansException;
import com.springframework.wzz.beans.BeanFactory;
import com.springframework.wzz.beans.factory.config.BeanDefinition;
import org.springframework.util.ObjectUtils;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    protected <T> T doGetBean(final String name, final Object[] args) {
        Object bean = getSingleton(name);
        if (!ObjectUtils.isEmpty(bean)) {
            return (T) bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String name) throws BeansException;

    protected abstract Object createBean(String name,BeanDefinition beanDefinition,Object[] args) throws BeansException;
}
