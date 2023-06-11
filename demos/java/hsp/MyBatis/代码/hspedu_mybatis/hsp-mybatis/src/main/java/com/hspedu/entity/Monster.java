package com.hspedu.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author 韩顺平
 * @version 1.0
 * Monster 和 monster表有映射关系
 *
 * 老师解读
 * @Getter 就会给所有属性 生成对应的getter
 * @Setter 就会给所有属性 生成对应的setter
 * @ToString 生成 toString...
 * @NoArgsConstructor 生成无参构造器
 * @AllArgsConstructor 生成要给全参构造器
 * @Data 注解
 * 如何选择主要还是看自己需要
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Monster {

    private Integer id;
    private Integer age;
    private String name;
    private String email;
    private Date birthday;
    private double salary;
    private Integer gender;

}
