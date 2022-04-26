package com.springframework.wzz;

import com.springframework.wzz.beans.factory.config.BeanDefinition;
import com.springframework.wzz.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test {

    /**
     * 大致流程就是，有一个注册容器和单例容器，bean里面先会添加金注册容器中，
     * 获取单例时发现单例容器中没有当前实例，然后去注册容器中获取，再添加进单例容器中
     * 下次获取单例容器就有值了
     * 其中还用到了模板模式，大致就是当前本类用到的方法给子类实现，
     * 本类不需要知道他的实现，只需要调用即可
     */
    @org.junit.jupiter.api.Test
    public void test_BeanFactory() {
        //初始化beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //注册bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        //第一次获取bean
//        UserService userService = (UserService) beanFactory.getBean("userService");
//        System.out.println(userService);
//        userService.queryUserInfo();

        //第二次获取bean 单例
        UserService userService_singleton = (UserService) beanFactory.getBean("userService","wzz");
        System.out.println(userService_singleton);
        userService_singleton.queryUserInfo();
    }
}
