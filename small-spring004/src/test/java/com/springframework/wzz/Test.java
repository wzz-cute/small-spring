package com.springframework.wzz;

import com.springframework.wzz.beans.PropertyValue;
import com.springframework.wzz.beans.PropertyValues;
import com.springframework.wzz.beans.factory.config.BeanDefinition;
import com.springframework.wzz.beans.factory.config.BeanReference;
import com.springframework.wzz.beans.factory.support.CglibSubclassingInstantiationStrategy;
import com.springframework.wzz.beans.factory.support.DefaultListableBeanFactory;
import com.springframework.wzz.beans.factory.support.InstantiationStrategy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

@SpringBootTest
public class Test {

    /**
     * 大致流程就是，有一个注册容器和单例容器，bean里面先会添加金注册容器中，
     * 获取单例时发现单例容器中没有当前实例，然后去注册容器中获取，再添加进单例容器中
     * 下次获取单例容器就有值了
     * 其中还用到了模板模式，大致就是当前本类用到的方法给子类实现，
     * 本类不需要知道他的实现，只需要调用即可
     *
     * 本示例即为，如果有入参等参数使用反射或者cglib代理将对象实例化出来
     */
    @org.junit.jupiter.api.Test
    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. UserDao 注册
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 3. UserService 设置属性[uId、userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

        // 4. UserService 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 5. UserService 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

}
