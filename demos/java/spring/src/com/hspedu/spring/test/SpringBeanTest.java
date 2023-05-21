package com.hspedu.spring.test;

import com.hspedu.spring.bean.*;
import com.hspedu.spring.component.MyComponent;
import com.hspedu.spring.component.UserAction;
import com.hspedu.spring.component.UserDao;
import com.hspedu.spring.component.UserService;
import com.hspedu.spring.depinjection.PhoneService;
import com.hspedu.spring.service.MemberServiceImpl;
import com.hspedu.spring.web.OrderAction;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class SpringBeanTest {

    //通过泛型依赖来配置Bean
    @Test
    public void setProByDependencyInjection() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans07.xml");
        PhoneService phoneService = ioc.getBean("phoneService", PhoneService.class);
        phoneService.save();
        System.out.println("ok");

    }

    //通过注解来配置Bean
    @Test
    public void setProByAutowired() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans06.xml");

        UserService userService = ioc.getBean("userService", UserService.class);
        System.out.println("ioc容器中的userService=" + userService);

        UserService userService200 = ioc.getBean("userService200", UserService.class);
        System.out.println("ioc容器中的userService200=" + userService200);

        UserAction userAction = ioc.getBean("userAction", UserAction.class);
        //System.out.println("userAction=" + userAction);
        userAction.sayOk();
    }

    //通过注解来配置Bean
    @Test
    public void setBeanByAnnotation() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans05.xml");

        UserDao userDao = ioc.getBean(UserDao.class);
        //在默认情况下, 注解标识的类创建对象后，在容器中，id 为类名的首字母小写
        UserDao userDao1 = ioc.getBean("userDao", UserDao.class);
        System.out.println("userDao1=" + userDao1);

        UserService userService = ioc.getBean(UserService.class);
        UserAction userAction = ioc.getBean(UserAction.class);
        MyComponent myComponent = ioc.getBean(MyComponent.class);


        System.out.println("userDao=" + userDao);
        System.out.println("userService=" + userService);
        System.out.println("userAction=" + userAction);
        System.out.println("myComponent=" + myComponent);





        System.out.println("ok");

    }

    //通过spring el 对属性赋值
    @Test
    public void setBeanBySpel() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans04.xml");


        SpELBean spELBean = ioc.getBean("spELBean", SpELBean.class);
        System.out.println("spELBean=" + spELBean);
    }


    //通过自动装配来对属性赋值
    @Test
    public void setBeanByAutowire() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans03.xml");


        OrderAction orderAction = ioc.getBean("orderAction", OrderAction.class);

        //验证是否自动装配上OrderService
        System.out.println(orderAction.getOrderService());
        //验证是否自动装配上OrderDao
        System.out.println(orderAction.getOrderService().getOrderDao());

    }

    //通过属性文件给bean属性赋值
    @Test
    public void setBeanByFile() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans03.xml");


        Monster monster1000 = ioc.getBean("monster1000", Monster.class);
        System.out.println("monster1000=" + monster1000);

    }



    @Test
    public void testBeanPostProcessor() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans02.xml");

        House house = ioc.getBean("house", House.class);
        System.out.println("使用house=" + house);

        House house02 = ioc.getBean("house02", House.class);
        System.out.println("使用house02=" + house02);

        ((ConfigurableApplicationContext)ioc).close();

    }

    //测试Bean的生命周期
    @Test
    public void testBeanLife() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        House house = ioc.getBean("house", House.class);

        System.out.println("使用house=" + house);

        //关闭容器
        //1. 这里又要考察大家的java基础
        //2. ioc的编译类型 ApplicationContext , 运行类型 ClassPathXmlApplicationContext
        //3. 因为ClassPathXmlApplicationContext 实现了 ConfigurableApplicationContext
        //4. ClassPathXmlApplicationContext 是有close
        //5. 将ioc 转成ClassPathXmlApplicationContext,再调用close
        //ioc.close();
        //关闭ioc容器.
        ((ConfigurableApplicationContext) ioc).close();


    }


    //测试Scope
    @Test
    public void testBeanScope() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        Cat cat = ioc.getBean("cat", Cat.class);
        Cat cat2 = ioc.getBean("cat", Cat.class);
        Cat cat3 = ioc.getBean("cat", Cat.class);
        System.out.println("cat=" + cat);
        System.out.println("cat2=" + cat2);
        System.out.println("cat3=" + cat3);

    }

    //测试Bean创建顺序
    @Test
    public void testBeanByCreate() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        System.out.println("ok");

    }

    //配置Bean通过继承
    @Test
    public void getBeanByExtends() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        Monster monster11 = ioc.getBean("monster11", Monster.class);
        System.out.println("monster11=" + monster11);

        Monster monster13 = ioc.getBean("monster13", Monster.class);
        System.out.println("monster13=" + monster13);


    }

    //通过FactoryBean获取bean
    @Test
    public void getBeanByFactoryBean() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        Monster my_monster05 = ioc.getBean("my_monster05", Monster.class);
        System.out.println("my_monster05=" + my_monster05);


    }

    //通过实例工厂获取bean
    @Test
    public void getBeanByInstanceFactory() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        Monster my_monster02 = ioc.getBean("my_monster02", Monster.class);
        //老韩要考察一把, 看看你是否理解
        Monster my_monster03 = ioc.getBean("my_monster03", Monster.class);
        System.out.println("my_monster02=" + my_monster02);
        System.out.println("my_monster03=" + my_monster03);

        System.out.println(my_monster02 == my_monster03);//false

    }

    //通过静态工厂获取bean
    //Java基础-静态和非静态
    @Test
    public void getBeanByStaticFactory() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        Monster my_monster01 = ioc.getBean("my_monster01", Monster.class);
        Monster my_monster04 = ioc.getBean("my_monster04", Monster.class);
        System.out.println("my_monster01=" + my_monster01);
        System.out.println(my_monster01 == my_monster04);//true

    }

    //给属性进行级联赋值
    @Test
    public void setBeanByRelation() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        Emp emp = ioc.getBean("emp", Emp.class);
        System.out.println("emp=" + emp);

    }

    //使用util:list名称空间给属性赋值
    @Test
    public void setBeanByUtilList() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        BookStore bookStore = ioc.getBean("bookStore", BookStore.class);
        System.out.println("bookStore=" + bookStore);

    }

    //给集合数组属性进行赋值
    @Test
    public void setBeanByCollection() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        Master master = ioc.getBean("master", Master.class);
        System.out.println("master=" + master);

    }

    //通过内部bean设置属性
    @Test
    public void setBeanByPro() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        MemberServiceImpl memberService2 =
                ioc.getBean("memberService2", MemberServiceImpl.class);

        memberService2.add();

    }

    //通过ref来设置bean属性
    @Test
    public void setBeanByRef() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        MemberServiceImpl memberService =
                ioc.getBean("memberService", MemberServiceImpl.class);

        memberService.add();

    }

    //通过p名称空间来设置属性
    @Test
    public void setBeanByP() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        Monster monster06 = ioc.getBean("monster06", Monster.class);
        System.out.println("monster06=" + monster06);
    }

    //通过构造器来设置属性
    @Test
    public void setBeanByConstructor() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        Monster monster03 = ioc.getBean("monster03", Monster.class);
        System.out.println("monster03=" + monster03);
    }

    //通过Bean的类型来获取对象
    @Test
    public void getBeanByType() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        //老师解读，直接传入class对象/类型
        Monster bean = ioc.getBean(Monster.class);
        System.out.println("bean=" + bean);
    }


    @Test
    public void getMonster() {

        //老师解读
        //1. 创建容器 ApplicationContext
        //2. 该容器和容器配置文件关联
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        //3. 通过getBean获取对应的对象
        //   默认返回的是Object , 但是运行类型Monster
        //Object monster01 = ioc.getBean("monster01");
        Monster monster01 = (Monster) ioc.getBean("monster01");

        //4. 输出
        System.out.println("monster01=" + monster01 + " 运行类型=" + monster01.getClass());
        System.out.println("monster01=" + monster01 + "属性name=" + monster01.getName() +
                " monserid=" + monster01.getMonsterId());


        //5. 也可以再获取的时候，直接指定Class类型, 可以在次获取
        Monster monster011 = ioc.getBean("monster01", Monster.class);
        System.out.println("monster011=" + monster011);
        System.out.println("monster011.name=" + monster011.getName());


        //6. 查看容器注入了哪些bean对象,会输出bean的id
        System.out.println("================================");
        String[] beanDefinitionNames = ioc.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName=" + beanDefinitionName);
        }
        System.out.println("================================");

        System.out.println("ok~~~");
    }

    //验证类加载路径
    @Test
    public void classPath() {


        File file = new File(this.getClass().getResource("/").getPath());
        //看到类的加载路径
        System.out.println("file=" + file);
    }

}
