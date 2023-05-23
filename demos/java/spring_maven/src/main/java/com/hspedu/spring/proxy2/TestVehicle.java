package com.hspedu.spring.proxy2;

import org.junit.jupiter.api.Test;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class TestVehicle {

    @Test
    public void run() {
        //OOP基础=>java基础
        Vehicle vehicle = new Ship();
        //动态绑定
        vehicle.run();
    }

    @Test
    public void proxyRun() {
        //创建Ship对象
        Vehicle vehicle = new Car();

        //创建VehicleProxyProvider对象, 并且我们传入的要代理的对象
        VehicleProxyProvider vehicleProxyProvider =
                new VehicleProxyProvider(vehicle);

        //获取代理对象, 该对象可以代理执行方法
        //老师解读
        //1. porxy 编译类型 Vehicle
        //2. 运行类型 是代理类型 class com.sun.proxy.$Proxy9

        Vehicle proxy = vehicleProxyProvider.getProxy();

        System.out.println("proxy的编译类型是 Vehicle");
        System.out.println("proxy的运行类型是 " + proxy.getClass());
        //下面老韩就要给大家解读/debug怎么 执行到 代理对象的 public Object invoke(Object o, Method method, Object[] args)
        //梳理完毕. proxy的编译类型是 Vehicle, 运行类型是 class com.sun.proxy.$Proxy9
        //所以当执行run方法时，会执行到 代理对象的invoke
        //如何体现动态 [1. 被代理的对象 2. 方法]
        //proxy.run();
        String result = proxy.fly(10000);
        System.out.println("result=" + result);
    }
}
