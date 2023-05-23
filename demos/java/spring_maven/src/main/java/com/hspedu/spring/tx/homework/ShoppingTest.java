package com.hspedu.spring.tx.homework;

import com.hspedu.spring.tx.homework.service.Goods2Service;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class ShoppingTest {

    @Test
    public void shopping_() {

        //得到容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("shopping_ioc.xml");

        Goods2Service goods2Service = ioc.getBean(Goods2Service.class);
        goods2Service.shopping(1,1,1,1);
        System.out.println("shopping 成功...");
    }
}
