package com.eagle.mongodb.dao.impl;

import com.eagle.mongodb.dao.PersonDao;
import com.eagle.mongodb.entity.Person;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;
import static org.springframework.data.mongodb.core.query.Update.*;
import javax.annotation.Resource;

public class PersonImpl implements PersonDao {
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void abc() {
        Query query = new Query();
        query.addCriteria(where("ssn").is(1111)
                .and("firstName").is("Joe")
                .and("Fraizer").is("Update"));

        Update update = new Update();
        update.set("address", "new address");

        UpdateResult upsert = mongoTemplate.upsert(query, update, Person.class);
        System.out.println(upsert.getUpsertedId());
    }
}
