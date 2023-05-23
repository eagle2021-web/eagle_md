package com.hspedu.spring.aop.proxy3;

import org.junit.jupiter.api.Test;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class AopTest {

    @Test
    public void smartDogTest() {

        SmartAnimalable smartAnimalable = new SmartDog();
        smartAnimalable.getSum(10, 2);
        System.out.println("=======================");
        smartAnimalable.getSub(10, 2);
    }

    @Test
    public void smartDogTestByProxy() {

        SmartAnimalable smartAnimalable = new SmartDog();

        MyProxyProvider myProxyProvider =
                new MyProxyProvider(smartAnimalable);

        //我们返回了代理对象
        SmartAnimalable proxy =
                myProxyProvider.getProxy();

        proxy.getSum(10, 2);
        System.out.println("====================");
        proxy.getSub(10, 2);

    }
}
