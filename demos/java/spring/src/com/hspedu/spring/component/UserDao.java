package com.hspedu.spring.component;

import org.springframework.stereotype.Repository;

/**
 * @author 韩顺平
 * @version 1.0
 * 使用 @Repository 标识该类是一个Repository是一个持久化层的类/对象
 *
 * 老师解读
 * 1. 标记注解后，类名首字母小写作为id的值(默认)
 * 2. value = "hspUserDao" 使用指定的 hspUserDao作为UserDao对象的id
 */
@Repository/*(value = "hspUserDao")*/
public class UserDao {
}
