package com.hspedu.hspmybatis.sqlsession;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author 韩顺平
 * @version 1.0
 * 读取xml文件, 建立连接
 */
public class HspConfiguration {

    //属性-类的加载器
    private static ClassLoader loader =
            ClassLoader.getSystemClassLoader();

    //读取xml文件信息，并处理
    public Connection build(String resource) {

        Connection connection = null;

        try {
            //加载配置hsp_mybatis.xml 获取到对应的InputStream
            InputStream stream =
                    loader.getResourceAsStream(resource);
            //解析hsp_mybatis.xml  => dom4j
            SAXReader reader = new SAXReader();
            Document document = reader.read(stream);
            //获取到hsp_mybatis.xml 的根元素 <database>
            Element root = document.getRootElement();
            System.out.println("root=" + root);
            //解析root元素，返回Connection => 老师准备单独的编写一个方法
            connection = evalDataSource(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    //方法会解析hsp_config.xml 信息,并返回Connection
    //eval: 评估/解析
    private Connection evalDataSource(Element node) {
        if (!"database".equals(node.getName())) {
            throw new RuntimeException("root 节点应该是<database>");
        }
        //连接DB的必要参数
        String driverClassName = null;
        String url = null;
        String username = null;
        String password = null;

        //遍历node下的子节点，获取属性值
        for (Object item : node.elements("property")) {
            Element i = (Element) item;//i 就是 对应property节点
            String name = i.attributeValue("name");
            String value = i.attributeValue("value");

            //判断是否得到name 和 value
            if (name == null || value == null) {
                throw new RuntimeException("property 节点没有设置name或者value属性");
            }
            switch (name) {
                case "url":
                    url = value;
                    break;
                case "username":
                    username = value;
                    break;
                case "driverClassName":
                    driverClassName = value;
                    break;
                case "password":
                    password = value;
                    break;
                default:
                    throw new RuntimeException("属性名没有匹配到...");
            }
        }

        Connection connection = null;

        try {
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection; //返回Connection
    }

}
