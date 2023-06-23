package org.spring.mongodb.example;

import com.mongodb.client.MongoClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.Resource;

import static org.springframework.data.mongodb.core.query.Criteria.where;
@ComponentScan(basePackages = "org.spring.mongodb.example")
public class MongoApp {

    private static final Log log = LogFactory.getLog(MongoApp.class);
    @Resource
    private MongoClient mongoClient;
    public static void main(String[] args) throws Exception {
        MongoApp mongoApp = new MongoApp();

        MongoOperations mongoOps = new MongoTemplate(mongoApp.mongoClient, "test");
        mongoOps.insert(new Person("Joe", 34));

        log.info(mongoOps.findOne(new Query(where("name").is("Joe")), Person.class));

//        mongoOps.dropCollection("person");
    }
}
