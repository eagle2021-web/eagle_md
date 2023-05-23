package com.hspedu.spring.aop.aspectj;

import org.springframework.stereotype.Component;

/**
 * @author 韩顺平
 * @version 1.0
 */
@Component //把Phone对象当做一个组件注入容器
public class Phone implements UsbInterface{
    @Override
    public void work() {
        System.out.println("手机开始工作了....");
    }
}
