package com.eagle.mysql;

import com.eagle.ServiceMysqlApplication;
import com.eagle.mysql.mapper.MonsterMapper;
import com.eagle.mysql.pojo.entity.Monster;
import com.eagle.mysql.service.IMonsterService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@Test
//@AutoConfigureMockMvc
public class AppContext {
    private GenericXmlApplicationContext ioc;
//    @Before(value = "")
//    public void before(){
//        ioc = new GenericXmlApplicationContext();
//        ConfigurableEnvironment environment = ioc.getEnvironment();
//        environment.setActiveProfiles("dev");
//        ioc.setEnvironment(environment);
//        ioc.load("abc.xml");
//        ioc.refresh();

//    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ioc = new GenericXmlApplicationContext();
        ConfigurableEnvironment environment = ioc.getEnvironment();
        environment.setActiveProfiles("dev");
        ioc.setEnvironment(environment);
        ioc.load("abc.xml");
        ioc.refresh();
        MonsterMapper monsterMapper = ioc.getBean("monsterMapper", MonsterMapper.class);
        Monster monster = new Monster();
        monster.setName("test");
        monsterMapper.addMonster2(monster);
        System.out.println(monster);
        IMonsterService service = ioc.getBean(IMonsterService.class);
        boolean b = service.removeById(10);
        System.out.println(b);
        System.out.println("ok");
        ioc.close();
    }

    @Test
    void acb(){

        ioc = new GenericXmlApplicationContext();
        ConfigurableEnvironment environment = ioc.getEnvironment();
        environment.setActiveProfiles("dev");
        ioc.setEnvironment(environment);
        ioc.load("abc.xml");
        ioc.refresh();
        MonsterMapper bean = ioc.getBean(MonsterMapper.class);
        System.out.println(bean.findMonsterByAge(10));
    }
}
