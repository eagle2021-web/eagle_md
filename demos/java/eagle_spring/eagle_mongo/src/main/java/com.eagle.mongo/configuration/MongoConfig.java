package com.eagle.mongo.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;

@Configuration
@PropertySource("classpath:mongo.properties")
public class MongoConfig {
    @Resource
    Environment env;

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(env.getProperty("mongo.url"));
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, "test");
    }
}
