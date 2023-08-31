package com.eagle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @ServletComponentScan与@ComponentScan是Spring框架中的两个注解，它们具有不同的功能和用途。
 *
 * @ServletComponentScan：这是一个Spring Boot注解，用于启用扫描并注册Servlet、Filter和Listener组件。当使用内嵌的Servlet容器（如Tomcat）运行Spring Boot应用程序时，可以使用该注解将带有@WebServlet、@WebFilter和@WebListener注解的类自动注册到Servlet容器中。
 *
 * 使用@ServletComponentScan注解时，不需要显式配置servlet、filter和listener的Bean，而是通过指定要扫描的包路径，自动发现并注册这些组件。
 *
 * @ComponentScan：这是一个Spring注解，用于启用组件扫描并注册Spring Bean。它会扫描指定包及其子包下的所有类，查找被@Component及其衍生注解（如@Service、@Repository等）标记的类，并将其实例化为Spring的Bean。
 *
 * 使用@ComponentScan注解时，可以在应用程序中扫描并注册自定义的所有组件，包括普通的POJO类、服务层组件、数据访问层组件等。通常用于构建应用程序的核心逻辑和业务层面的组件。
 *
 * 总结来说，如果需要自动注册Servlet、Filter和Listener组件，以便在Tomcat或其他内嵌的Servlet容器中使用，可以使用@ServletComponentScan注解。而如果只想扫描和注册Spring Bean组件（不涉及Servlet组件），则应使用@ComponentScan注解。
 *
 * 选择使用哪个注解取决于你的具体需求和项目情况。如果你正在构建基于Spring Boot的Web应用程序，并且需要自动注册Servlet组件，那么@ServletComponentScan是一个很方便的选项。否则，如果你只需要扫描和注册普通的Spring Bean组件，那么@ComponentScan注解就足够了。
 */
//(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
@ServletComponentScan("com.eagle")
//@ComponentScan(
//        basePackages = "com.eagle",
//        useDefaultFilters = false,
//        includeFilters = {
//                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Service.class),
//                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class),
//                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Repository.class),
//                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Component.class),
//                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
//        }
//)
//@EntityScan("com.eagle.mysql.pojo.entity")
//@MapperScan(basePackages = "com.eagle.mysql.convertor")
public class ServiceMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMysqlApplication.class, args);
    }


}
