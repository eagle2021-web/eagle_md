package com.hspedu.spring.hspapplicationcontext;

import com.hspedu.spring.bean.Monster;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class HspApplicationContextTest {
    public static void main(String[] args) throws Exception {

        HspApplicationContext ioc =
                new HspApplicationContext("beans.xml");

        Monster monster01 = (Monster)ioc.getBean("monster01");

        System.out.println("monter01=" + monster01);
        System.out.println("monster01.name=" + monster01.getName());
        System.out.println("ok");
    }
}
