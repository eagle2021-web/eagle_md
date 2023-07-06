package com.eagle.base.pojo.reflect;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class Student {
    private String name;
}
@Data
class Person {
    Object student; //我希望他可以映射Student，也可以映射String

    public void setStudent(Object student) {
        System.out.println(student);
        System.out.println(student.getClass());
        this.student = student;
    }

    public static void main(String[] args) {
        String s = "{\n" +
                "  \"student\": {\n" +
                "    \"name\": \"2112\"\n" +
                "  }\n" +
                "}";
        Person person = JSONObject.parseObject(s, Person.class);
        System.out.println(person);
        s = "  {\n" +
                "    \"student\": \"2112\"\n" +
                "}";
        if(person.getStudent() instanceof Student){
            System.out.println("122222222");
        }
        Person person1 = JSONObject.parseObject(s, Person.class);
        System.out.println(person1);
    }
}


