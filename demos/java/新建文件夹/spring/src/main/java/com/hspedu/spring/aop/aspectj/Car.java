package com.hspedu.spring.aop.aspectj;

import org.springframework.stereotype.Component;

/**
 * @author 韩顺平
 * @version 1.0
 */
@Component //把Car作为一个组件[对象]，注入到spring容器
public class Car {
    public void run() {
        System.out.println("小汽车在running..");
    }
}
