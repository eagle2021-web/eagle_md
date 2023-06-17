package com.hspedu.test;

import com.hspedu.entity.Monster;
import com.hspedu.hspmybatis.sqlsession.Executor;
import com.hspedu.hspmybatis.sqlsession.HspConfiguration;
import com.hspedu.hspmybatis.sqlsession.HspExecutor;
import com.hspedu.hspmybatis.sqlsession.HspSqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class HspMyBatisTest {

    @Test
    public void build() {
        HspConfiguration hspConfiguration = new HspConfiguration();
        Connection connection = hspConfiguration.build("hsp_mybatis.xml");
        System.out.println("connection--" + connection);
    }


    @Test
    public void query() {
        Executor executor = new HspExecutor();
        Monster monster =
                executor.query("select * from monster where id=?", 1);
        System.out.println("monster-- " + monster);
//        DefaultSqlSession defaultSqlSession = new DefaultSqlSession();
    }

    @Test
    public void selectOne() {
        HspSqlSession hspSqlSession = new HspSqlSession();
        Object o = hspSqlSession.selectOne("select * from monster where id=?", 1);
        System.out.println(o);
    }

    @Test
    public void readMapper() throws DocumentException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        HspConfiguration hspConfiguration = new HspConfiguration();
        hspConfiguration.readMapper("MonsterMapper.xml");
    }
}
