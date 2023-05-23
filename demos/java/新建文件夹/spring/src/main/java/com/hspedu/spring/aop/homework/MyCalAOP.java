package com.hspedu.spring.aop.homework;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author 韩顺平
 * @version 1.0
 */
@Aspect //MyCalAOP 是一个切面类
@Component //MyCalAOP/对象 作为组件注入到spring容器
public class MyCalAOP {

    //前置通知
    //这里注意,如果目标类和切面类，在同一个包，可以省略包名
    //因为cal1和cal2方法,都要去输出开始执行时间,因此使用MyCal.*
    @Before(value = "execution(public int MyCal.*(int))")
    public void calStart(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println(signature.getName() + " 执行, 开始执行时间=" + System.currentTimeMillis());

    }

    //返回通知
    //这里注意,如果目标类和切面类，在同一个包，可以省略包名
    //因为cal1和cal2方法,都要去输出开始执行时间,因此使用MyCal.*
    @AfterReturning(value = "execution(public int MyCal.*(int))")
    public void calEnd(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println(signature.getName() + " 执行, 结束时间=" + System.currentTimeMillis());

    }
}
