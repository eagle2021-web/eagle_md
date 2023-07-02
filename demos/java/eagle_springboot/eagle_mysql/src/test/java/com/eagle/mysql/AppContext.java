package com.eagle.mysql;

import com.eagle.ServiceMysqlApplication;
import com.eagle.mysql.dao.MonsterDao;
import com.eagle.mysql.dao.impl.MonsterDaoImpl;
import com.eagle.mysql.mapper.MonsterMapper;
import com.eagle.mysql.pojo.entity.Monster;
import com.eagle.mysql.service.IMonsterService;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeAliasRegistry;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
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
    private static GenericXmlApplicationContext ioc;
    @BeforeAll
    public static void before(){
        ioc = new GenericXmlApplicationContext();


        ConfigurableEnvironment environment = ioc.getEnvironment();
        environment.setActiveProfiles("dev");
        ioc.setEnvironment(environment);
        ioc.load( "abc.xml");
        ioc.refresh();
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ioc = new GenericXmlApplicationContext();
        ConfigurableEnvironment environment = ioc.getEnvironment();
        environment.setActiveProfiles("dev");
        ioc.setEnvironment(environment);
        ioc.load( "abc.xml");
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
        MonsterMapper bean = ioc.getBean(MonsterMapper.class);
        System.out.println(bean.findMonsterByAge(10));
    }

    @Test
    void findMonsterById_foreach(){
        MonsterMapper bean = ioc.getBean(MonsterMapper.class);
        List<Monster> monsterById_foreach = bean.findMonsterById_foreach(Arrays.asList(10, 11, 12));
        System.out.println(monsterById_foreach);
    }

    @Test
    void updateMonster(){
        MonsterMapper bean = ioc.getBean(MonsterMapper.class);
        Monster monster = Monster.builder()
                .id(10).age(18).name("eagle22").build();
        bean.updateMonsterById(monster);
    }

    @Test
    void selectMonsterByIdAndAge(){
        MonsterDaoImpl bean = ioc.getBean(MonsterDaoImpl.class);
        Monster monster = Monster.builder()
                .id(10).build();
        bean.selectMonsterByIdAndAge(monster);
    }
}
