package com.eagle.mysql.component;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * 定义一个统计方法执行耗时的切面类
 *
 * @author wpw
 */
@Aspect
@Component//使用spring容器进行管理
@Slf4j
public class CountTimeAspect {
    /**
     * 首先定义一个切点
     */
    @Pointcut("execution(* com.eagle.mysql.controller..*.*(..))")
    public void countTime() {

    }

    @Around("countTime()")
    public Object doAround(ProceedingJoinPoint pjd) throws Throwable {
        log.info("环绕通知开始");
        // 获取方法名
        String className = pjd.getSignature().getClass().getName();
        // 获取执行的方法名称
        String methodName = pjd.getSignature().getName();
        log.info("方法名称：：{}", methodName);
        // 记录开始时间
        long start = System.currentTimeMillis();
        // 获取方法参数
        Object[] args = pjd.getArgs();
        StringBuilder params = new StringBuilder("前端请求参数为:");
        //获取请求参数集合并进行遍历拼接
        for (Object object : args) {
            params.append(object.toString()).append(",");
        }
        params.setLength(params.length() - 1);
        //打印请求参数参数
        log.info("{}类的{}的{}", className, methodName, params);
        // 执行目标方法
        Object result = pjd.proceed();
        // 打印返回报文
        log.info("方法返回为：{}", result);
        // 获取执行完的时间
        log.info("{}方法执行时长为:{}", methodName, (System.currentTimeMillis() - start));
        log.info("环绕通知结束");
        return result;
    }
}


