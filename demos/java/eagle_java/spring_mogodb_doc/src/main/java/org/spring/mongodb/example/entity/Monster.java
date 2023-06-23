package org.spring.mongodb.example.entity;

import lombok.Data;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Data
@Configuration
@PropertySource("classpath:monster.properties")
public class Monster {
    @Resource
    Environment env;

}
