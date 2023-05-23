package com.hspedu.spring.aop.homework.xml;




/**
 * @author 韩顺平
 * @version 1.0
 */
public class MyCal implements Cal {
    @Override
    public int cal1(int n) {
        int res = 1;
        for (int i = 1; i <= n; i++) {
            res += i;
        }
        System.out.println("cal1 执行结果=" + res);
        return res;
    }

    @Override
    public int cal2(int n) {
        int res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        System.out.println("cal2 执行结果=" + res);
        return res;
    }
}
