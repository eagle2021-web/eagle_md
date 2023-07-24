package com.eagle.mysql.controller;


import com.eagle.mysql.config.MybatisPlusConfig;
import com.eagle.mysql.mapper.MonsterMapper;
import com.eagle.mysql.mapper.convertor.HealthConvertor;
import com.eagle.mysql.mapper.convertor.HealthConvertorImpl;
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

import javax.annotation.Resource;

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
    @Resource(name = "healthConvertorImpl")
    private HealthConvertor healthConvertor;
    @GetMapping("")
    public String getHealth() {
        Health health = new Health();
        health.setName("adsf");
        health.setAge(12);
        return healthConvertor.from(health).toString();
    }




}

