package com.hspedu.spring.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author 韩顺平
 * @version 1.0
 */
//也可以使用@Service
@Component
public class UserService {

    //定义属性
    //老师思考：加入 @Autowired , Spring容器时如何实现依赖注入?
    //也可以使用@Resource
    @Autowired
    private UserDao userDao;

    public void m1() {
        userDao.hi();
    }

    //这里我们需要指定init() 是初始化方法
    @PostConstruct
    public void init() {
        System.out.println("UserService-init()");
    }

}
