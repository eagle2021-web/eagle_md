package org.spring.mongodb.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author eagle2020
 */
//(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
@ServletComponentScan("com.eagle")
public class ServiceMysqlPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMysqlPlusApplication.class, args);
    }
}