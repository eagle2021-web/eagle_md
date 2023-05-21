package com.hspedu.spring.test;

import com.hspedu.spring.bean.Monster;
import com.hspedu.spring.jdbctemplate.dao.MonsterDao;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class JdbcTemplateTest {

    @Test
    public void testDatasourceByJdbcTemplate() throws SQLException {
        //获取到容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");

        DataSource dataSource = ioc.getBean(DataSource.class);
        Connection connection = dataSource.getConnection();
        System.out.println("获取到connection= " + connection);
        connection.close();
        System.out.println("ok");
    }

    //测试通过JdbcTemplate对象完成添加数据
    @Test
    public void addDataByJdbcTemplate() {
        //获取到容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");

        //获取JdbcTemplate对象
        JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);
        //1. 添加方式1
        //String sql = "INSERT INTO monster VALUES(400, '红孩儿', '枪法')";
        //jdbcTemplate.execute(sql);
        //2. 添加方式2
        String sql = "INSERT INTO monster VALUES(?, ?, ?)";
        //affected表示 执行后表受影响的记录数
        int affected = jdbcTemplate.update(sql, 500, "红孩儿2", "枪法2");
        System.out.println("add ok affected=" + affected);


    }

    //测试通过JdbcTemplate对象完成修改数据
    @Test
    public void updateDataByJdbcTemplate() {

        //获取到容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");

        //获取JdbcTemplate对象
        JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);

        //组织SQL
        String sql = "UPDATE monster SET skill=? WHERE id=?";
        int affected = jdbcTemplate.update(sql, "美女计", 300);
        System.out.println("update ok affected= " + affected);

    }

    //批量添加二个monster 白蛇精和青蛇精
    //这里有一个使用API的技巧
    /**
     * 老师说明
     * 1. 对于某个类， 有很多API, 使用的步骤
     * 2. 老韩的使用技巧（1） 先确定API名字 (2) 根据API提供相应的参数 [组织参数]
     *    (3) 把自己的调用思路清晰 (4) 根据API, 可以推测类似的用法和功能
     */

    /**
     * batch add data
     * 批量添加二个monster 白蛇精和青蛇精-update(sql,List<Object[]>)
     */
    @Test
    public void addBatchDataByJdbcTemplate() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        //得到JdbcTemplate bean
        JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);//添加..

        //1. 先确定,猜测API名称 batchUpdate[如果出现问题，才重新玩]
        //public int[] batchUpdate(String sql, List<Object[]> batchArgs){}
        //2. 准备参数
        String sql = "INSERT INTO monster VALUES(?, ?, ?)";
        List<Object[]> batchArgs = new ArrayList<>();
        batchArgs.add(new Object[]{600, "老鼠精", "偷吃粮食"});
        batchArgs.add(new Object[]{700, "老猫精", "抓老鼠"});
        //3. 调用
        //说明：返回结果是一个数组，每个元素对应上面的sql语句对表的影响记录数
        int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
        //输出
        for (int anInt : ints) {
            System.out.println("anInt=" + anInt);
        }
        System.out.println("batch add ok..");
    }

    //查询id=100的monster并封装到Monster实体对象[在实际开发中，非常有用]

    @Test
    public void selectDataByJdbcTemplate() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        //得到JdbcTemplate bean
        JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);
        //组织SQL
        //通过BeanPropertyRowMapper获取rowmapper 是一个接口，可以将查询的结果，封装到你指定的Monster对象中.

        //1. 确定API ： queryForObject()
        //public <T> T queryForObject(String sql, RowMapper<T> rowMapper, @Nullable Object... args)
        //2.准备参数
        String sql = "SELECT id AS monsterId, NAME, skill FROM monster WHERE id = 100";
        //使用RowMapper 接口来对返回的数据，进行一个封装-》底层使用的反射->setter
        //这里有一个细节: 你查询的记录的表的字段需要和 Monster的对象字段名保持一致
        RowMapper<Monster> rowMapper = new BeanPropertyRowMapper<>(Monster.class);
        //jdbcTemplate
        Monster monster = jdbcTemplate.queryForObject(sql, rowMapper);
        System.out.println("monster= " + monster);
        System.out.println("查询ok");

    }
    //查询id>=200的monster并封装到Monster实体对象

    /**
     * 查询多条记录
     */
    @Test
    public void selectMulDataByJdbcTemplate() {
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        //得到JdbcTemplate bean
        JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);
        //组织SQL
        //通过BeanPropertyRowMapper获取rowmapper 是一个接口，可以将查询的结果，封装到你指定的Monster对象中.

        //1.    确定API
        //public <T> T query(String sql, RowMapper<T> rowMapper, Object... args){}
        //2. 组织参数
        String sql = "SELECT id AS monsterId, NAME, skill FROM monster WHERE id >= ?";
        RowMapper<Monster> rowMapper = new BeanPropertyRowMapper<>(Monster.class);
        //3. 调用
        List<Monster> monsterList = jdbcTemplate.query(sql, rowMapper, 100);
        for (Monster monster : monsterList) {
            System.out.println("monster= " + monster);
        }
    }

    //查询返回结果只有一行一列的值，比如查询id=100的怪物名

    /**
     * 查询返回结果只有一行一列的值
     */
    @Test
    public void selectScalarByJdbcTemplate() {
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        //得到JdbcTemplate bean
        JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);

        //1. 确定API
        //public <T> T queryForObject(String sql, Class<T> requiredType)
        //2. 提供参数
        String sql = "SELECT NAME FROM monster WHERE id = 100";
        //Class<T> requiredType 表示你返回的单行单列的数据类型

        String name =
                jdbcTemplate.queryForObject(sql, String.class);
        System.out.println("返回name= " + name);

    }

    //使用Map传入具名参数完成操作，比如添加 螃蟹精.:name 就是具名参数形式需要使用NamedParameterJdbcTemplate 类, 语句形式: String sql = "INSERT INTO monster VALUES(:my_id, :name, :skill)";

    /**
     * 使用Map传入具名参数完成操作，比如添加
     */
    @Test
    public void testDataByNamedParameterJdbcTemplate() {
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        //得到NamedParameterJdbcTemplate bean
        NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                ioc.getBean(NamedParameterJdbcTemplate.class);

        //1. 确定使用API
        //public int update(String sql, Map<String, ?> paramMap)
        //2. 准备参数 [:my_id, :name, :skill] 要求按照规定的名字来设置参数
        String sql = "INSERT INTO monster VALUES(:id, :name, :skill)";
        Map<String, Object> paramMap = new HashMap<>();
        //给paramMap填写数据
        paramMap.put("id", 800);
        paramMap.put("name", "蚂蚁精");
        paramMap.put("skill", "喜欢打洞");
        //3. 调用
        int affected = namedParameterJdbcTemplate.update(sql, paramMap);
        System.out.println("add ok affected=" + affected);


    }

    //使用sqlparametersoruce 来封装具名参数,还是添加一个Monster 狐狸精

    @Test
    public void operDataBySqlparametersoruce() {
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        //得到NamedParameterJdbcTemplate bean
        NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                ioc.getBean(NamedParameterJdbcTemplate.class);

        //确定API
        //public int update(String sql, SqlParameterSource paramSource)
        //public BeanPropertySqlParameterSource(Object object)
        //准备参数
        String sql = "INSERT INTO monster VALUES(:monsterId, :name, :skill)";
        Monster monster = new Monster(900, "大象精", "搬运木头");
        SqlParameterSource sqlParameterSource =
                new BeanPropertySqlParameterSource(monster);
        //调用
        int affected =
                namedParameterJdbcTemplate.update(sql, sqlParameterSource);

        System.out.println("add ok affected= " + affected);
    }


    //测试MonsterDAO
    @Test
    public void monsterDaoSave() {
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");

        MonsterDao monsterDao = ioc.getBean(MonsterDao.class);
        Monster monster = new Monster(1000, "小鸭精", "吃鱼");
        monsterDao.save(monster);
        System.out.println("MonsterDAO保存 ok ..");
    }
}
