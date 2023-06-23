package com.eagle.mongo.entity;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

@Data
@Configuration
@PropertySource("classpath:monster.properties")
public class Monster {
    @Resource
    Environment env;

}
