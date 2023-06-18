package com.hspedu.mapper;

import com.hspedu.entity.Monster;
import com.hspedu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class MonsterMapperTest {
    //属性
    private SqlSession sqlSession;
    private MonsterMapper monsterMapper;

    /**
     * 老师解读
     * 1. 当方法标注 @Before, 表示在执行你的目标测试方法前，会先执行该方法
     * 2. 这里在测试的时候，可能小伙伴们会遇到一些麻烦，老师说了解决方案
     */
    //编写方法完成初始化
    @Before
    public void init() {
        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        //获取到到MonsterMapper对象 class com.sun.proxy.$Proxy7 代理对象
        //, 底层是使用了动态代理机制, 后面我们自己实现mybatis底层机制时，会讲到
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);
        System.out.println("monsterMapper=" + monsterMapper.getClass());

    }

    @Test
    public void addMonster() {

        for (int i = 0; i < 2; i++) {
            Monster monster = new Monster();
            monster.setAge(10 + i);
            monster.setBirthday(new Date());
            monster.setEmail("kate@qq.com");
            monster.setGender(1);
            monster.setName("大象精-");
            monster.setSalary(1000 + i * 10);
            monsterMapper.addMonster(monster);

            System.out.println("添加对象--" + monster);
            System.out.println("添加到表中后, 自增长的id=" + monster.getId());
        }

        //如果是增删改, 需要提交事务
        if(sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }

        System.out.println("保存成功...");

    }

    @Test
    public void delMonster() {

        monsterMapper.delMonster(2);

        if(sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }

        System.out.println("删除成功...");

    }

    @Test
    public void updateMonster() {

        Monster monster = new Monster();
        monster.setAge(50);
        monster.setBirthday(new Date());
        monster.setEmail("king3@qq.com");
        monster.setGender(0);
        monster.setName("老鼠精-01");
        monster.setSalary(2000);
        monster.setId(3);

        monsterMapper.updateMonster(monster);

        if(sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }

        System.out.println("修改成功...");

    }

    @Test
    public void getMonsterById() {

        Monster monster = monsterMapper.getMonsterById(3);
        System.out.println("monster=" + monster);

        if(sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("查询成功~");
    }


    @Test
    public void findAllMonster() {

        List<Monster> monsters = monsterMapper.findAllMonster();
        for (Monster monster : monsters) {
            System.out.println("monster-" + monster);
        }
        if(sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("查询成功~");
    }


    @Test
    public void getMonsterByIdOrName() {
        Monster monster1 = new Monster();
        monster1.setName("eagle0618");
        monster1.setId(2);
        Monster monster = monsterMapper.getMonsterByIdOrName(monster1);
        if(sqlSession != null) {
            sqlSession.close();
        }
        System.out.println(monster);
        System.out.println("查询成功~");
    }
}
