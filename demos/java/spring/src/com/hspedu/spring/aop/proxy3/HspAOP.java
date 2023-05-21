package com.hspedu.spring.aop.proxy3;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author 韩顺平
 * @version 1.0
 * 我们自己编写一个一个极简的AOP类
 */
public class HspAOP {

    //我们一个方法,在目标对象执行前执行
    public static void before(Object proxy, Method method, Object[] args) {
        System.out.println("HspAOP-方法执行前-日志-方法名-" + method.getName() + "-参数 "
                + Arrays.asList(args)); //这里从AOP看，就是一个横切关注点-前置通知
    }

    //我们一个方法,在目标对象执行后执行
    public static void after(Method method, Object result) {
        System.out.println("HspAOP-方法执行正常结束-日志-方法名-" + method.getName() + "-结果result= "
                + result);//从AOP看, 也是一个横切关注点-返回通知
    }
}
