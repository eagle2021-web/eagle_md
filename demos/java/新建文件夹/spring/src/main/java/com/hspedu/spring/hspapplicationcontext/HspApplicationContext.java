package com.hspedu.spring.hspapplicationcontext;

import com.hspedu.spring.bean.Monster;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 韩顺平
 * @version 1.0
 * 老师解读
 * 1. 这个程序用于实现Spring的一个简单容器机制
 * 2. 后面我们还会详细的实现
 * 3. 这里我们实现如何将beans.xml文件进行解析，并生成对象,放入容器中
 * 4. 提供一个方法 getBean(id) 返回对应的对象
 * 5. 这里就是一个开胃小点心, 理解Spring容器的机制
 */
public class HspApplicationContext {

    private ConcurrentHashMap<String, Object> singletonObjects =
            new ConcurrentHashMap<>();


    //构造器
    //接收一个容器的配置文件 比如 beans.xml, 该文件默认在src
    public HspApplicationContext(String iocBeanXmlFile) throws Exception {

        //1. 得到类加载路径
        String path = this.getClass().getResource("/").getPath();

        //2. 创建 Saxreader
        SAXReader saxReader = new SAXReader();

        //3. 得到Document对象
        Document document =
                saxReader.read(new File(path + iocBeanXmlFile));

        //4. 得到rootDocument
        Element rootElement = document.getRootElement();

        //5. 得到第一个bean-monster01
        Element bean = (Element) rootElement.elements("bean").get(0);

        //6. 获取到第一个bean-monster01的相关属性
        String id = bean.attributeValue("id");
        String classFullPath = bean.attributeValue("class");
        List<Element> property = bean.elements("property");
        //遍历->老师简化直接获取
        Integer monsterId =
                Integer.parseInt(property.get(0).attributeValue("value"));

        String name = property.get(1).attributeValue("value");
        String skill = property.get(2).attributeValue("value");

        //7. 使用反射创建对象.=> 回顾反射机制
        Class<?> aClass = Class.forName(classFullPath);
        //这里o对象就是Monster对象
        Monster o = (Monster) aClass.newInstance();
        //给o对象赋值
        //反射来赋值=> 这里老师就简化，直接赋值->目的就是先理解流程
        //这里的方法就是setter方法
        //Method[] declaredMethods = aClass.getDeclaredMethods();
        //for (Method declaredMethod : declaredMethods) {
        //    declaredMethod.invoke();
        //}
        //赋值
        o.setMonsterId(monsterId);
        o.setName(name);
        o.setSkill(skill);

        //8. 将创建好的对象放入到singletonObjects
        singletonObjects.put(id, o);

    }

    public Object getBean(String id) {
        //这里小伙伴可以在处理
        return singletonObjects.get(id);
    }
}
