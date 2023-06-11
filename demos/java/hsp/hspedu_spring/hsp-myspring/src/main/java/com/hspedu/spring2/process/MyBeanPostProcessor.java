package com.hspedu.spring2.process;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author 韩顺平
 * @version 1.0
 * 编写的一个后置处理器
 */
//@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * 在Bean的 init初始化方法前调用-> 这个知识点，在前面讲解后置处理器时讲过的
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        System.out.println("postProcessBeforeInitialization 被 调 用 " + beanName + " bean= " + bean.getClass());
        return bean;
    }

    /**
     * 在Bean的 init初始化方法后调用-> 这个知识点，在前面讲解后置处理器时讲过的
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization 被 调 用 " + beanName + " bean= " + bean.getClass());
        return bean;
    }
}
