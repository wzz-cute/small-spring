package com.springframework.wzz.beans.factory.support;

import com.springframework.wzz.BeansException;
import com.springframework.wzz.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    private InstantiationStrategy strategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition,Object[] args) throws BeansException {
        Object bean = null;
        try {
//            bean = beanDefinition.getBean().newInstance();
            bean = createBeanInstance(beanDefinition,name,args);
        }catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(name,bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBean();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();

        //将所有的构造器参数对比
        for (Constructor ctor : declaredConstructors) {
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return strategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.strategy = instantiationStrategy;
    }
}
