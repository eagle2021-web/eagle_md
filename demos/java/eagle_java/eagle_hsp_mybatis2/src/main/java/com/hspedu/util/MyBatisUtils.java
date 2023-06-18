package com.hspedu.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 韩顺平
 * @version 1.0
 * MyBatisUtils 工具类，可以得到SqlSession
 */
public class MyBatisUtils {

    //属性
    private static SqlSessionFactory sqlSessionFactory;

    //编写静态代码块-初始化sqlSessionFactory
    static {
        try {
            //指定资源文件, 配置文件mybatis-config.xml
            String resource = "mybatis-config.xml";
            //获取到配置文件mybatis-config.xml 对应的inputStream
            //这里老师说明:加载文件时，默认到resources目录=>运行后的工作目录target-classes
            InputStream resourceAsStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            System.out.println("sqlSessionFactory="
                    + sqlSessionFactory.getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //编写方法，返回SqlSession对象-会话
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
