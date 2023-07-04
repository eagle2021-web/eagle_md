package com.eagle.base.pojo.entity;

interface Organization {
    <Y extends Person> void deploy(Y y);

    //    <T extends Person> void add(T y);
//    <Z extends Person> void modify(Z y);
}

interface Person {
    void say();
}

class Student implements Person {

    @Override
    public void say() {

    }
}

class Department implements Organization {
    @Override
    public void deploy(Person person) {
    }

}

public class Animal {
}

abstract class Component{
    private String name;
    public String queryName(){
        return name;
    }
}
class Ginger extends Component{
    private final String name = "生姜";

}
interface Drugs<T extends Component> {
    void addComponent( T t);
}

class ThroatTablet implements Drugs<Ginger> {
    @Override
    public void addComponent(Ginger ginger) {

    }
}
