package com.hspedu.spring.aop.aspectj;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class AopAspectjTest {

    @Test
    public void smartDogTestByProxy() {

        //得到spring容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans08.xml");
        //这里我们需要通过接口类型来获取到注入的SmartDog对象-就是代理对象
        SmartAnimalable smartAnimalable =
                ioc.getBean(SmartAnimalable.class);

        //SmartAnimalable smartAnimalable =
        //        (SmartAnimalable)ioc.getBean("smartDog");

        smartAnimalable.getSum(10, 2);

        //System.out.println("smartAnimalable运行类型="
        //        + smartAnimalable.getClass());

        System.out.println("=============================");

        //smartAnimalable.getSub(100, 20);


    }

    @Test
    public void smartDogTestByProxy2() {

        //得到spring容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans08.xml");

        UsbInterface phone = (UsbInterface) ioc.getBean("phone");
        UsbInterface camera = (UsbInterface) ioc.getBean("camera");

        phone.work();

        System.out.println("==================");

        camera.work();

    }

    @Test
    public void test3() {
        //得到spring容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans08.xml");

        Car car = ioc.getBean(Car.class);

        //说明: car对象仍然是代理对象
        System.out.println("car的运行类型=" + car.getClass());

        car.run();

    }

    @Test
    public void testDoAround() {
        //得到spring容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans08.xml");


        SmartAnimalable smartAnimalable =
                ioc.getBean(SmartAnimalable.class);

        smartAnimalable.getSum(10, 2);
    }

}
