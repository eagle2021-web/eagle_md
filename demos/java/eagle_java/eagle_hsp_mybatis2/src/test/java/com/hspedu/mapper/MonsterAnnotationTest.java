package com.hspedu.mapper;

import com.hspedu.entity.Monster;
import com.hspedu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MonsterAnnotationTest {
    private SqlSession sqlSession;
    private MonsterAnnotation monsterAnnotation;
    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
        monsterAnnotation = sqlSession.getMapper(MonsterAnnotation.class);
        System.out.println("monsterAnnotation = " + monsterAnnotation.getClass());
    }

    @Test
    public void getMonsterById() {
        System.out.println(monsterAnnotation.getMonsterById(1));
    }

    @Test
    public void addMonster() {
        Monster monster = new Monster();
        monster.setAge(12);
        monster.setName("eagle0618");
        monsterAnnotation.addMonster(monster);
        System.out.println("返回的id = " + monster.getId());
    }

    @After
    public void commit() {
        sqlSession.commit(true);
    }
}
