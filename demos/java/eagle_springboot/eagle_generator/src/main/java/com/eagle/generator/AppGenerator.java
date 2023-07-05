package com.eagle.generator;

import org.apache.ibatis.annotations.Param;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.sql.DataSourceDefinition;

@SpringBootApplication
@ServletComponentScan("com.eagle")
public class AppGenerator {
    public static void main(String[] args) {
        SpringApplication.run(AppGenerator.class, args);
    }
    @Configuration
    public class WebConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")  // 匹配所有的请求路径
                    .allowedOrigins("*")  // 允许所有的源（Origin）发起跨域请求
                    .allowedMethods("*")  // 允许所有的 HTTP 方法
                    .allowedHeaders("*");  // 允许所有的请求头

            // 更多可选配置：
            // .allowCredentials(true)  // 允许携带凭证（如 Cookie）
            // .exposedHeaders("header1", "header2")  // 指定允许前端访问的响应头
            // .maxAge(3600);  // 预检请求的有效期（单位：秒）
        }
    }
    @RestController
    @RequestMapping("/hello")
    static class HelloController {
        @GetMapping("/say")
        public String hello(@RequestParam("say") String content){
            System.out.println(content);
            return "ok";
        }
    }
}
