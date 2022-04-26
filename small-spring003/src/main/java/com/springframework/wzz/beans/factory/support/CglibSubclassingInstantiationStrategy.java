package com.springframework.wzz.beans.factory.support;

import com.springframework.wzz.BeansException;
import com.springframework.wzz.beans.factory.config.BeanDefinition;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Constructor;

/**
 * 其实 Cglib 创建有构造函数的 Bean 也非常方便，在这里我们更加简化的处理了，如果你阅读 Spring 源码还会看到 CallbackFilter 等实现，不过我们目前的方式并不会影响创建。
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(beanDefinition.getClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });

        if (ObjectUtils.isEmpty(ctor)) return enhancer.create();

        return enhancer.create(ctor.getExceptionTypes(), args);

    }
}
