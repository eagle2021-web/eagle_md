package com.hspedu.spring.homework;

import com.hspedu.spring.bean.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class Homework02 {
    public static void main(String[] args) {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("car_beans.xml");

        Car car01 = ioc.getBean("car01", Car.class);
        System.out.println("car01=" + car01);
        System.out.println("car01.name=" + car01.getName());
        System.out.println("ok");
    }
}
