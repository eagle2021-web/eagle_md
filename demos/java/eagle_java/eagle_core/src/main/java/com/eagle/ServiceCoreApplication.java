package com.eagle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Controller;

/**
 * @author eagle2020
 */
//(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
@ComponentScan(
        basePackages = "com.eagle",
        useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Service.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Repository.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Component.class)
        }
)
public class ServiceCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCoreApplication.class, args);
    }

}