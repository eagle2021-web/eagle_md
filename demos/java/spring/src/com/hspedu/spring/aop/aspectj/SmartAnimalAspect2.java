package com.hspedu.spring.aop.aspectj;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author 韩顺平
 * @version 1.0
 * 切面类 , 类似于我们以前自己写的MyProxyProvider,但是功能强大很多
 */
//@Aspect //表示是一个切面类[底层切面编程的支撑(动态代理+反射+动态绑定...)]
//@Component //会注入SmartAnimalAspect2到容器
public class SmartAnimalAspect2 {

    //演示环绕通知的使用-了解
    //老师解读
    //1. @Around: 表示这是一个环绕通知[完成其它四个通知的功能]
    //2. value = "execution(public float com.hspedu.spring.aop.aspectj.SmartDog.getSum(float, float)) 切入点表达式
    //3. doAround 表示要切入的方法 - 调用结构 try-catch-finally
    @Around(value = "execution(public float com.hspedu.spring.aop.aspectj.SmartDog.getSum(float, float))")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        Object result = null;
        String methodName = joinPoint.getSignature().getName();
        try {
            //1.相当于前置通知完成的事情
            Object[] args = joinPoint.getArgs();
            List<Object> argList = Arrays.asList(args);
            System.out.println("AOP环绕通知[-前置通知]" + methodName + "方法开始了--参数有：" + argList);
            //在环绕通知中一定要调用joinPoint.proceed()来执行目标方法
            result = joinPoint.proceed();
            //2.相当于返回通知完成的事情
            System.out.println("AOP环绕通知[-返回通知]" + methodName + "方法结束了--结果是：" + result);
        } catch (Throwable throwable) {
            //3.相当于异常通知完成的事情
            System.out.println("AOP环绕通知[-异常通知]" + methodName + "方法抛异常了--异常对象：" + throwable);
        } finally {
            //4.相当于最终通知完成的事情
            System.out.println("AOP环绕通知[-后置通知]" + methodName + "方法最终结束了...");
        }
        return result;
    }
}
