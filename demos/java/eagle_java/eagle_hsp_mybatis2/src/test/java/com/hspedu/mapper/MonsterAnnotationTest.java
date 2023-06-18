package com.hspedu.mapper;

import com.hspedu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
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
}
