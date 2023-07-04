package com.eagle.base.pojo.entity;

import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.Data;
import lombok.Setter;

import java.util.Comparator;

public class StudentCompare {

    @Data
    static class Student implements Comparator<Student> {
        private int id;
        private String name;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }


        @Override
        public String toString() {
            return "Student [id=" + id + ", name=" + name + "]";
        }

        @Override
        public int compare(Student s1, Student s2) {
            return Comparator.comparing(Student::getId)
                    .thenComparing(Student::getName)
                    // 按需继续添加其他字段的比较
                    .compare(s1, s2);
        }
    }


}
