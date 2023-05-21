package com.hspedu.spring.bean;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class Cat {
    private Integer id;
    private String name;

    public Cat() {
        //System.out.println("Cat() 被执行...");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //@Override
    //public String toString() {
    //    return "Cat{" +
    //            "id=" + id +
    //            ", name='" + name + '\'' +
    //            '}';
    //}
}
