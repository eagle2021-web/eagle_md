package com.eagle.mysql.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author eagle2020
 */
@Configuration
@MapperScan("com.eagle.mysql.mapper")
@EnableTransactionManagement
@SuppressWarnings("all")
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //分页
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }


    @Profile("dev")
    @Bean(destroyMethod = "close")
    public DataSource dataSource() throws NullPointerException {
        System.out.println("dev");
        String password = "123456";
        if (password == null) {
            throw new NullPointerException("no mysql password");
        }
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://h131:3306/hsp_mybatis?serverTimezone=GMT%2B8&characterEncoding=utf-8");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");

        dataSource.setPassword(password);
        dataSource.setInitialSize(20);
        dataSource.setMaxConnLifetimeMillis(30000);
        return dataSource;
    }

    @Bean(destroyMethod = "close")
    @Profile("prod222")
    public DataSource dataSource2() throws NullPointerException {
        System.out.println("prod");
        String password = System.getenv("MYSQL_PASSWORD");
        if (password == null) {
            throw new NullPointerException("no mysql password");
        }
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://h131:3306/hsp_mybatis?serverTimezone=GMT%2B8&characterEncoding=utf-8");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");

        dataSource.setPassword(password);
        dataSource.setInitialSize(20);
        dataSource.setMaxConnLifetimeMillis(30000);
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource); // 设置数据源
        // 创建Mybatis的全局配置对象
        GlobalConfig globalConfig = new GlobalConfig();
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        dbConfig.setLogicDeleteField("flag");
        dbConfig.setLogicDeleteValue("1");
        dbConfig.setLogicNotDeleteValue("0");
        globalConfig.setDbConfig(dbConfig);
        // 应用全局配置
        factoryBean.setGlobalConfig(globalConfig);
        // 配置mapper XML文件的位置
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factoryBean.setMapperLocations(resolver.getResources("classpath:com/eagle/mysql/mapper/xml/*.xml"));
        factoryBean.setGlobalConfig(globalConfig);

//        org.apache.ibatis.session.Configuration cfg = factoryBean.getObject().getConfiguration();
//        TypeAliasRegistry registry = cfg.getTypeAliasRegistry();
//        registry.registerAliases("com.eagle.mysql.pojo.entity");
//        registry.registerAlias("Monster", com.eagle.mysql.pojo.entity.Monster.class);

//        factoryBean.setConfiguration(cfg);
        return factoryBean.getObject();
    }
}