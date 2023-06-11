package com.hspedu.spring2;

import com.hspedu.spring2.aop.SmartAnimalable;
import com.hspedu.spring2.component.UserAction;
import com.hspedu.spring2.component.UserDao;
import com.hspedu.spring2.component.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URL;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class AppMain {
    public static void main(String[] args) {
        //测试看看是否可以得到spring容器中的bean , 同时看看依赖注入是否OK

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");
        UserAction userAction = (UserAction) ioc.getBean("userAction");
        UserAction userAction2 = (UserAction) ioc.getBean("userAction");

        System.out.println("userAction=" + userAction);
        System.out.println("userAction2=" + userAction2);

        UserDao userDao = (UserDao) ioc.getBean("userDao");
        System.out.println("userDao=" + userDao);

        UserService userService = (UserService) ioc.getBean("userService");
        System.out.println("userService=" + userService);

        // //测试一下当前的依赖注入
        // userService.m1();

        // //测试一下AOP
        // SmartAnimalable smartDog = ioc.getBean(SmartAnimalable.class);
        // smartDog.getSum(10, 2);





    }
}
