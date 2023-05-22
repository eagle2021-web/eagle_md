package com.hspedu.spring.bean;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class House {
    private String name;

    public House() {
        System.out.println();
        System.out.println("House() 构造器...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("House setName()=" + name);
        this.name = name;
    }

    //老师解读
    //1. 这个方法是程序员来编写的.
    //2. 根据自己的业务逻辑来写.
    public void init() {
        System.out.println("House init()..");
        setName("深圳豪宅");
    }

    //老师解读
    //1. 这个方法是程序员来编写的.
    //2. 根据自己的业务逻辑来写.
    //3. 名字也不是固定的
    public void destroy() {
        System.out.println("House destroy()..");
    }

    @Override
    public String toString() {
        return "House{" +
                "name='" + name + '\'' +
                '}';
    }
}
