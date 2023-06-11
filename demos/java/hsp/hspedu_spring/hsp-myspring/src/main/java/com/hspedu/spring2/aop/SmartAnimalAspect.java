package com.hspedu.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author 韩顺平
 * @version 1.0
 * 这是一个切面类
 */
@Component
@Aspect
public class SmartAnimalAspect {

    //给SmartDog配置前置，返回，异常，最终通知

    //前置通知
    @Before(value = "execution(public float com.hspedu.spring.aop.SmartDog.getSum(float, float))")
    public void showBeginLog(JoinPoint joinPoint) {
        //通过连接点对象joinPoint 可以获取方法签名
        Signature signature = joinPoint.getSignature();
        System.out.println("SmartAnimalAspect-切面类showBeginLog()[使用的myPointCut()]-方法执行前-日志-方法名-" + signature.getName() + "-参数 "
                + Arrays.asList(joinPoint.getArgs()));
    }

   //返回通知
    @AfterReturning(value = "execution(public float com.hspedu.spring.aop.SmartDog.getSum(float, float))", returning = "res")
    public void showSuccessEndLog(JoinPoint joinPoint, Object res) {
        Signature signature = joinPoint.getSignature();
        System.out.println("SmartAnimalAspect-切面类showSuccessEndLog()-方法执行正常结束-日志-方法名-" + signature.getName() + " 返回的结果是=" + res);
    }


   //异常通知
    @AfterThrowing(value = "execution(public float com.hspedu.spring.aop.SmartDog.getSum(float, float))", throwing = "throwable")
    public void showExceptionLog(JoinPoint joinPoint, Throwable throwable) {
        Signature signature = joinPoint.getSignature();
        System.out.println("SmartAnimalAspect-切面类showExceptionLog()-方法执行异常-日志-方法名-" + signature.getName() + " 异常信息=" + throwable);
    }

    //最终通知
    @After(value = "execution(public float com.hspedu.spring.aop.SmartDog.getSum(float, float))")
    public void showFinallyEndLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println("SmartAnimalAspect-切面类showFinallyEndLog()-方法最终执行完毕-日志-方法名-" + signature.getName());
    }

}
