package com.hspedu.spring.aop.proxy3;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class SmartDog implements SmartAnimalable {
    @Override
    public float getSum(float i, float j) {
        //System.out.println("日志-方法名-getSum-参数 " + i + " " + j);
        float result = i + j;
        System.out.println("方法内部打印result = " + result);
        //System.out.println("日志-方法名-getSum-结果result= " + result);
        return result;
    }

    @Override
    public float getSub(float i, float j) {
        //System.out.println("日志-方法名-getSub-参数 " + i + " " + j);
        float result = i - j;
        System.out.println("方法内部打印result = " + result);
        //System.out.println("日志-方法名-getSub-结果result= " + result);
        return result;
    }
}
