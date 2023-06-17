package com.hspedu.hspmybatis.sqlsession;

import com.hspedu.entity.Monster;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class HspExecutor implements Executor {

    //属性
    private final HspConfiguration hspConfiguration =
            new HspConfiguration();


    /**
     * 根据 sql 查找结果
     *
     * @param sql
     * @param parameter
     * @param <T>
     * @return
     */
    @Override
    public <T> T query(String sql, Object parameter) {
        //得到连接Connection
        Connection connection = getConnection();
        //查询返回的结果集
        ResultSet set = null;
        PreparedStatement pre = null;

        try {
            pre = connection.prepareStatement(sql);
            //设置参数, 如果参数多, 可以使用数组处理.
            pre.setString(1, parameter.toString());
            set = pre.executeQuery();
            //把set数据封装到对象-monster
            //老师说明: 这里老师做了简化处理
            //认为返回的结果就是一个monster记录
            //完善的写法是一套反射机制.
            Monster monster = new Monster();

            //遍历结果集, 把数据封装到monster对象
            while (set.next()) {
                monster.setId(set.getInt("id"));
                monster.setName(set.getString("name"));
                monster.setEmail(set.getString("email"));
                monster.setAge(set.getInt("age"));
                monster.setGender(set.getInt("gender"));
                monster.setBirthday(set.getDate("birthday"));
                monster.setSalary(set.getDouble("salary"));
            }
            return (T) monster;

        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (set != null) {
                    set.close();
                }
                if (pre != null) {
                    pre.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }

        return null;
    }

    //编写方法,通过HspConfiguration对象，返回连接
    private Connection getConnection() {
        Connection connection =
                hspConfiguration.build("hsp_mybatis.xml");
        return connection;
    }
}
