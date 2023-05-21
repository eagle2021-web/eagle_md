package com.hspedu.spring.aop.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;

/**
 * @author 韩顺平
 * @version 1.0
 *
 * 这是我们开发一个切面类, 但是不用注解，而是使用XML配置
 */
public class SmartAnimalAspect {


    public void showBeginLog(JoinPoint joinPoint) {
        //通过连接点对象joinPoint 可以获取方法签名
        Signature signature = joinPoint.getSignature();
        System.out.println("SmartAnimalAspect-XML配置-切面类showBeginLog()[使用的myPointCut()]-方法执行前-日志-方法名-" + signature.getName() + "-参数 "
                + Arrays.asList(joinPoint.getArgs()));
    }

    public void showSuccessEndLog(JoinPoint joinPoint, Object res) {
        Signature signature = joinPoint.getSignature();
        System.out.println("SmartAnimalAspect-XML配置-切面类showSuccessEndLog()-方法执行正常结束-日志-方法名-" + signature.getName() + " 返回的结果是=" + res);
    }


    public void showExceptionLog(JoinPoint joinPoint, Throwable throwable) {
        Signature signature = joinPoint.getSignature();
        System.out.println("SmartAnimalAspect-XML配置-切面类showExceptionLog()-方法执行异常-日志-方法名-" + signature.getName() + " 异常信息=" + throwable);
    }

    public void showFinallyEndLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println("SmartAnimalAspect-XML配置-切面类showFinallyEndLog()-方法最终执行完毕-日志-方法名-" + signature.getName());
    }
}
