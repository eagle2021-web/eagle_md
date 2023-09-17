package com.eagle.mysql.controller;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/mongo")
@Slf4j
public class MongoController {
    @Resource
    private MongoTemplate template;
    @GetMapping("/add")
    public String add(){
        MongoCollection<Document> java = template.getCollection("java");
        FindIterable<Document> documents = java.find();
        documents.forEach(System.out::println);
        return "ok";
    }

}
