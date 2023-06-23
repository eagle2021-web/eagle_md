package org.spring.mongodb.example;

import org.junit.jupiter.api.Test;
import org.spring.mongodb.example.entity.Monster;
import org.spring.mongodb.example.configuration.MongoConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

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
        Monster bean = ioc.getBean(Monster.class);
        System.out.println(bean.getEnv().getProperty("monster.name"));
        System.out.println(bean);
        System.out.println("--------");
        MongoConfig config = ioc.getBean(MongoConfig.class);
        System.out.println(config);
        System.out.println("--------");
        MongoTemplate template = ioc.getBean(MongoTemplate.class);
        System.out.println(template.save(new Person("sdfdsf", 12)));
    }

}
