package com.hspedu.spring.annotation;

import com.hspedu.spring.hspapplicationcontext.HspApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 韩顺平
 * @version 1.0
 * HspSpringApplicationContext 类的作用类似Spring原生ioc容器
 */
public class HspSpringApplicationContext {
    private Class configClass;
    //ioc我存放的就是通过反射创建的对象(基于注解方式)
    private final ConcurrentHashMap<String, Object> ioc =
            new ConcurrentHashMap<>();

    //构造器
    public HspSpringApplicationContext(Class configClass) {

        this.configClass = configClass;
        //System.out.println("this.configClass=" + this.configClass);
        //获取要扫描的包
        //1. 先得到HspSpringConfig配置的的@ComponentScan(value = "com.hspedu.spring.component")
        ComponentScan componentScan =
                (ComponentScan) this.configClass.getDeclaredAnnotation(ComponentScan.class);
        //2. 通过componentScan的value=> 即要扫描的包
        String path = componentScan.value();
        System.out.println("要扫描的包= " + path);

        //得到要扫描的包下的所有资源(类 .class)
        //1.得到类的加载器
        ClassLoader classLoader =
                HspApplicationContext.class.getClassLoader();
        //2. 通过类的加载器获取到要扫描的包的资源 url=》类似一个路径
        path = path.replace(".", "/");//一定要把. 替换成 /
        URL resource =
                classLoader.getResource(path);
        System.out.println("resource=" + resource);
        //3. 将要加载的资源(.class) 路径下的文件进行遍历=>io
        File file = new File(resource.getFile());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                System.out.println("=====================");
                System.out.println("=" + f.getAbsolutePath());
                //D:\hspedu_spring\spring\out\production\spring\com\hspedu\spring\component\UserService.class
                //获取到 com.hspedu.spring.component.UserService
                String fileAbsolutePath = f.getAbsolutePath();

                //这里我们只处理.class文件
                if (fileAbsolutePath.endsWith(".class")) {

                    //1. 获取到类名
                    String className =
                            fileAbsolutePath.substring(fileAbsolutePath.lastIndexOf("\\") + 1, fileAbsolutePath.indexOf(".class"));
                    //System.out.println("className=" + className);
                    //2. 获取类的完整的路径(全类名)
                    //老师解读 path.replace("/",".") => com.hspedu.spring.component.
                    String classFullName = path.replace("/", ".") + "." + className;
                    //System.out.println("classFullName=" + classFullName);

                    //3. 判断该类是不是需要注入容器, 就看该类是不是有注解 @Component @Service..
                    try {
                        //这时，我们就得到老该类的Class对象
                        //Class clazz = Class.forName(classFullName)
                        //老师说一下
                        //1. Class clazz = Class.forName(classFullName) 可以反射加载类
                        //2. classLoader.loadClass(classFullName); 可以反射类的Class
                        //3. 区别是 : 上面方式后调用来类的静态方法, 下面方法不会
                        //4. aClass.isAnnotationPresent(Component.class) 判断该类是否有 @Component
                        Class<?> aClass = classLoader.loadClass(classFullName);
                        if (aClass.isAnnotationPresent(Component.class) ||
                                aClass.isAnnotationPresent(Controller.class) ||
                                aClass.isAnnotationPresent(Service.class) ||
                                aClass.isAnnotationPresent(Repository.class)) {

                            //这里老师演示一个Component注解指定value,分配id
                            //老师就是演示了一下机制.
                            if(aClass.isAnnotationPresent(Component.class)) {
                                //获取到该注解
                                Component component = aClass.getDeclaredAnnotation(Component.class);
                                String id = component.value();
                                if(!"".endsWith(id)) {
                                    className = id;//替换
                                }
                            }

                            //这时就可以反射对象，并放入到容器中
                            Class<?> clazz = Class.forName(classFullName);
                            Object instance = clazz.newInstance();
                            //放入到容器中, 将类名的首字母小写作为id
                            //StringUtils

                            ioc.put(StringUtils.uncapitalize(className) , instance);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    //编写方法返回对容器中对象
    public Object getBean(String name) {
        return ioc.get(name);
    }
}
