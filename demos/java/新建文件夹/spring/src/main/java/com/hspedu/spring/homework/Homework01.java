package com.hspedu.spring.homework;

import com.hspedu.spring.bean.Monster;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class Homework01 {

    @Test
    public void getMonster() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        Monster monster01 = ioc.getBean("com.hspedu.spring.bean.Monster#0", Monster.class);
        System.out.println("monster01=" + monster01);
        System.out.println("monster01.monsterId=" + monster01.getMonsterId());

        Monster monster02 = ioc.getBean("com.hspedu.spring.bean.Monster#1", Monster.class);
        System.out.println("monster02=" + monster02);
        System.out.println("monster02.monsterId=" + monster02.getMonsterId());

        System.out.println("ok~");
    }
}
