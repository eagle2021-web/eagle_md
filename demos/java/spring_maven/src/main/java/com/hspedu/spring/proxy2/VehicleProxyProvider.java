package com.hspedu.spring.proxy2;



import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 韩顺平
 * @version 1.0
 * VehicleProxyProvider 该类可以返回一个代理对象.
 */
public class VehicleProxyProvider {

    //定义一个属性
    //target_vehicle 表示真正要执行的对象
    //该对象实现了Vehicle接口
    private Vehicle target_vehicle;

    //构造器
    public VehicleProxyProvider(Vehicle target_vehicle) {
        this.target_vehicle = target_vehicle;
    }

    //编写一个方法，可以返回一个代理对象, 该代理对象可以通过反射机制调用到被代理对象的方法
    //老师解读
    //1. 这个方法非常重要， 理解有一定难度
    public Vehicle getProxy() {

        //得到类加载器
        ClassLoader classLoader =
                target_vehicle.getClass().getClassLoader();

        //得到要代理的对象/被执行对象 的接口信息,底层是通过接口来完成调用
        Class<?>[] interfaces = target_vehicle.getClass().getInterfaces();


        //创建InvocationHandler 对象
        //因为 InvocationHandler 是接口，所以我们可以通过匿名对象的方式来创建该对象
        /**
         *
         * public interface InvocationHandler {
         *  public Object invoke(Object proxy, Method method, Object[] args)
         *         throws Throwable;
         * }
         * invoke 方法是将来执行我们的target_vehicle的方法时，会调用到
         *
         */

        InvocationHandler invocationHandler = new InvocationHandler() {
            /**
             * invoke 方法是将来执行我们的target_vehicle的方法时，会调用到
             * @param o 表示代理对象
             * @param method 就是通过代理对象调用方法时，的哪个方法 代理对象.run()
             * @param args : 表示调用 代理对象.run(xx) 传入的参数
             * @return 表示 代理对象.run(xx) 执行后的结果.
             * @throws Throwable
             */
            @Override
            public Object invoke(Object o, Method method, Object[] args)
                    throws Throwable {

                System.out.println("交通工具开始运行了....");
                //这里是我们的反射基础 => OOP
                //method 是？: public abstract void com.hspedu.spring.proxy2.Vehicle.run()
                //target_vehicle 是? Ship对象
                //args 是null
                //这里通过反射+动态绑定机制，就会执行到被代理对象的方法
                //执行完毕就返回
                Object result = method.invoke(target_vehicle, args);
                System.out.println("交通工具停止运行了....");
                return result;
            }
        };

        /*

          public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h)

          老师解读
          1. Proxy.newProxyInstance() 可以返回一个代理对象
          2. ClassLoader loader: 类的加载器.
          3. Class<?>[] interfaces 就是将来要代理的对象的接口信息
          4. InvocationHandler h 调用处理器/对象 有一个非常重要的方法invoke
         */
        Vehicle proxy =
                (Vehicle)Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);

        return proxy;
    }
}
