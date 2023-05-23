package com.hspedu.spring.aop.xml;


/**
 * @author 韩顺平
 * @version 1.0
 */
//@Component //使用@Component 当spring容器启动时，将 SmartDog注入到容器
public class SmartDog implements SmartAnimalable {
    @Override
    public float getSum(float i, float j) {
        float result = i + j;
        //result = 1 / 0; //模拟一个算术异常
        System.out.println("方法内部打印result = " + result);
        return result;
    }

    @Override
    public float getSub(float i, float j) {
        float result = i - j;
        System.out.println("方法内部打印result = " + result);
        return result;
    }
}
