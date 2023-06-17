package com.hspedu.hspmybatis.sqlsession;

import com.hspedu.config.Function;
import com.hspedu.config.MapperBean;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author 韩顺平
 * @version 1.0
 * 读取xml文件, 建立连接
 */
public class HspConfiguration {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        DriverManager.getConnection("jdbc:mysql://h131:3306?useSSL=true&requireSSL=true&verifyServerCertificate=false",
                "root",
                "123456");
    }
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
        System.out.println(url);
        System.out.println(username);
        System.out.println(password);
        System.out.println(driverClassName);
        Connection connection = null;

        try {
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(connection);
        return connection; //返回Connection
    }

    /**
     * 读取xxxMapper.xml能够创建MapperBean对象
     * @param path 就是xml的路径 + 文件名，是从类的加载路径计算的
     *             如果xxxMapper.xml文件是放在resources目录下，直接传入xml文件名即可
     * @return
     */
    public MapperBean readMapper(String path) throws DocumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        MapperBean mapperBean = new MapperBean();
        InputStream stream = loader.getResourceAsStream(path);
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(stream);
        Element rootElement = document.getRootElement();
        System.out.println("root = ");
        System.out.println(rootElement);
        String namespace = rootElement.attributeValue("namespace").trim();
        mapperBean.setInterfaceName(namespace);
        Iterator iterator = rootElement.elementIterator();
        ArrayList<Function> list = new ArrayList<>();
        mapperBean.setFunctions(list);
        while (iterator.hasNext()) {
            // 取出一个子元素
            Element next = (Element)iterator.next();
            Function function = new Function();
            String sqlType = next.getName().trim();
            System.out.println(sqlType);
            String resultType = next.attributeValue("resultType").trim();
            String funcName = next.attributeValue("id").trim();
            System.out.println(resultType);
            String sql = next.getTextTrim();
            function.setSqlType(sqlType);
            function.setFuncName(funcName);
            function.setResultType(resultType);
            function.setSql(sql);

            // 通过反射生成一个对象
            Object newInstance = Class.forName(resultType).getConstructor().newInstance();
            function.setResultType(newInstance);
            list.add(function);
        }
        System.out.println(mapperBean);
        return mapperBean;
    }
}
