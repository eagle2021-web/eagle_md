package com.hspedu.spring.aop.homework;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class TestMyCalAOP {

    @Test
    public void testMyCalByAnnotation() {

        //得到spring容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans10.xml");

        Cal cal = ioc.getBean(Cal.class);

        cal.cal1(10);
        System.out.println("===========");
        cal.cal2(5);
    }
}
