package org.spring.mongodb.example.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author eagle2020
 */
//(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
@ServletComponentScan("com.eagle")
public class ServiceMongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMongodbApplication.class, args);
    }

}

