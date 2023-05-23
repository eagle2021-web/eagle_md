package com.hspedu.spring.aop.xml;


import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class AopAspectjXMLTest {


    @Test
    public void testAspectByXML() {

        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans09.xml");
        SmartAnimalable smartAnimalable =
                ioc.getBean(SmartAnimalable.class);

        smartAnimalable.getSum(10, 2);
    }

}
