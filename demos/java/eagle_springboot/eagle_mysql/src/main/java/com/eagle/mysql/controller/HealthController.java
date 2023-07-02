package com.eagle.mysql.controller;


import com.eagle.mysql.config.MybatisPlusConfig;
import com.eagle.mysql.mapper.MonsterMapper;
import com.eagle.mysql.pojo.entity.Health;
import com.eagle.mysql.pojo.entity.Monster;
import com.eagle.mysql.service.IMonsterService;
import com.eagle.mysql.service.impl.MonsterServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author eagle
 * @since 2022-11-15
 */
@RestController
@RequestMapping("/health")

public class HealthController {
//    @Resource
//    private HealthService healthService;

    @GetMapping("")
    public String getHealth() {
        return "sdfsdf";
    }




}

