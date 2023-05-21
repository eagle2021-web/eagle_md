package com.hspedu.spring.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author 韩顺平
 * @version 1.0
 * 可以返回一个动态代理对象, 可以执行SmartDog对象的方法
 */
public class MyProxyProvider {

    //定义我们要执行的目标对象, 该对象需要实现SmartAnimalable
    private SmartAnimalable target_obj;

    //构造器
    public MyProxyProvider(SmartAnimalable target_obj) {
        this.target_obj = target_obj;
    }

    //方法, 可以返回代理对象，该代理对象可以执行目标对象
    public SmartAnimalable getProxy() {

        //1. 先到的类加载器/对象
        ClassLoader classLoader = target_obj.getClass().getClassLoader();

        //2. 得到要执行的目标对象的接口信息
        Class<?>[] interfaces = target_obj.getClass().getInterfaces();

        //3. 创建InvocationHandler
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result = null;
                try {
                    System.out.println("方法执行前-日志-方法名-" + method.getName() + "-参数 "
                            + Arrays.asList(args)); //这里从AOP看，就是一个横切关注点-前置通知
                    //使用反射调用方法
                    result = method.invoke(target_obj, args);
                    System.out.println("方法执行正常结束-日志-方法名-" + method.getName() + "-结果result= "
                            + result);//从AOP看, 也是一个横切关注点-返回通知

                } catch (Exception e) {
                    e.printStackTrace();
                    //如果反射执行方法时，出现异常,就会进入到catch{}
                    System.out.println("方法执行异常-日志-方法名-" + method.getName()
                            + "-异常类型=" + e.getClass().getName());//从AOP看, 也是一个横切关注点-异常通知
                } finally {//不管你是否出现异常,最终都会执行到finally{}
                    //从AOP的角度看, 也是一个横切关注点-最终通知
                    System.out.println("方法最终结束-日志-方法名-" + method.getName());
                }

                return result;
            }
        };

        //创建代理对象
        SmartAnimalable proxy =
                (SmartAnimalable)Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        return proxy;
    }
}
