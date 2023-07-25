package com.eagle.mysql.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.eagle.mysql.service.SwService;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonObject;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import static com.mongodb.client.model.Filters.*;
import javax.annotation.Resource;
@Component
public class SwServiceImpl implements SwService {
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void saveJson(String content) {
        JSONObject jsonObject = JSONObject.parseObject(content);
        String purl = jsonObject.getJSONObject("metadata").getJSONObject("component")
                        .getString("purl");
        System.out.println(purl);
        Document parse = Document.parse(content);
        Query query = new Query(Criteria.where("metadata.component.purl").is(purl));
        Update update = Update.fromDocument(parse);
        mongoTemplate.upsert(query, update, "java");
    }

    @Override
    public Document queryJson(String purl) {
        MongoCollection<Document> collection = mongoTemplate.getCollection("java");
        Bson query = eq("metadata.component.purl", purl);
        FindIterable<Document> result = collection.find(query).projection(Projections.excludeId());
        Document first = result.first();
        return first;
    }
}
