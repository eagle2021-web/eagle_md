package com.eagle.mysql.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.eagle.mysql.service.SwService;
import org.bson.Document;
import org.bson.json.JsonObject;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
@Component
public class SwServiceImpl implements SwService {
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void saveJson(String content) {
        mongoTemplate.getCollectionNames().forEach(System.out::println);
//        mongoTemplate.createCollection("java");
        mongoTemplate.getCollectionNames().forEach(System.out::println);

//        JSONObject jsonObject = JSONObject.parseObject(content);
//        String purl = jsonObject.getString("metadata.component.purl");
//        Query query = new Query(Criteria.where("metadata.component.purl").is(purl));
//        Update update = new Update();
//        mongoTemplate.updateFirst(query, update, content);
//        Document parse = Document.parse(content);
//        mongoTemplate.
        mongoTemplate.save(content, "java");

    }
}
