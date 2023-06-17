package com.hspedu.test;

import com.hspedu.entity.Monster;
import com.hspedu.hspmybatis.sqlsession.Executor;
import com.hspedu.hspmybatis.sqlsession.HspConfiguration;
import com.hspedu.hspmybatis.sqlsession.HspExecutor;
import org.junit.Test;

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
    }

}
