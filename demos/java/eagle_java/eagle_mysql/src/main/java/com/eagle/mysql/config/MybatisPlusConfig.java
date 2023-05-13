package com.eagle.mysql.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author eagle2020
 */
@Configuration
@MapperScan("com.eagle.mysql.mapper")
@EnableTransactionManagement
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
    public DataSource dataSource() throws NullPointerException{
        System.out.println("dev");
        String password = System.getenv("MYSQL_PASSWORD");
        if(password == null){
            throw new NullPointerException("no mysql password");
        }
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8&characterEncoding=utf8");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");

        dataSource.setPassword(password);
        dataSource.setInitialSize(20);
        dataSource.setMaxActive(30);
        return dataSource;
    }

    @Bean(destroyMethod = "close")
    @Profile("prod222")
    public DataSource dataSource2() throws NullPointerException{
        System.out.println("prod");
        String password = System.getenv("MYSQL_PASSWORD");
        if(password == null){
            throw new NullPointerException("no mysql password");
        }
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8&characterEncoding=utf8");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");

        dataSource.setPassword(password);
        dataSource.setInitialSize(20);
        dataSource.setMaxActive(30);
        return dataSource;
    }

    public static void main(String[] args) {
    }
}