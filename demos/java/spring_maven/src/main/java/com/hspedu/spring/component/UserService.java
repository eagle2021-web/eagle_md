package com.hspedu.spring.component;

import org.springframework.stereotype.Service;

/**
 * @author 韩顺平
 * @version 1.0
 * @Service 标识该类是一个Service类/对象
 */
@Service
public class UserService {
    //方法..
    public void hi(){
        System.out.println("UserService hi()~");
    }
}
