package com.hspedu.spring.aop.homework.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;


/**
 * @author 韩顺平
 * @version 1.0
 */
public class MyCalAOP {

    //前置通知

    public void calStart(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println(signature.getName() + " 基于XML配置- 执行, 开始执行时间=" + System.currentTimeMillis());

    }

    //返回通知
    public void calEnd(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println(signature.getName() + " 基于XML配置- 执行, 结束时间=" + System.currentTimeMillis());

    }
}
