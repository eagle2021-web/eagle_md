package com.hspedu.spring.bean;

/**
 * @author 韩顺平
 * @version 1.0
 * JavaBean / Entity
 */
public class Monster {
    private Integer monsterId;
    private String name;
    private String skill;

    //全参构造器
    public Monster(Integer monsterId, String name, String skill) {
        this.monsterId = monsterId;
        this.name = name;
        this.skill = skill;
    }


    //无参构造器一定要写，Spring反射创建对象时，需要使用
    public Monster() {
    }

    public Integer getMonsterId() {
        return monsterId;
    }

    public void setMonsterId(Integer monsterId) {
        this.monsterId = monsterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "monsterId=" + monsterId +
                ", name='" + name + '\'' +
                ", skill='" + skill + '\'' +
                '}';
    }
}
