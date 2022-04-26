package com.springframework.wzz.beans.factory.support;

import com.springframework.wzz.beans.factory.config.BeanDefinition;

/**
 * 注册bean
 */
public interface BeanDefinitionRegistry {
    /**
     * 向注册表中注册 BeanDefinition
     *
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
