package com.hspedu.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author 韩顺平
 * @version 1.0
 * 这是一个后置处理器, 需要实现 BeanPostProcessor接口
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * 什么时候被调用: 在Bean的init方法前被调用
     * @param bean 传入的在IOC容器中创建/配置Bean
     * @param beanName 传入的在IOC容器中创建/配置Bean的id
     * @return Object 程序员对传入的bean 进行修改/处理【如果有需要的话】 ,返回
     * @throws BeansException
     */

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization().. bean="
                + bean + " beanName=" + beanName);

        //初步体验案例: 如果类型是House的统一改成 上海豪宅
        //对多个对象进行处理/编程==>切面编程
        if(bean instanceof House) {
            ((House)bean).setName("上海豪宅~");
        }
        return null;
    }

    /**
     * 什么时候被调用: 在Bean的init方法后被调用
     * @param bean  传入的在IOC容器中创建/配置Bean
     * @param beanName 传入的在IOC容器中创建/配置Bean的id
     * @return 程序员对传入的bean 进行修改/处理【如果有需要的话】 ,返回
     * @throws BeansException
     */

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization().. bean="
                + bean + " beanName=" + beanName);
        return bean;
    }
}
