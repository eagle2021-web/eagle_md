package com.eagle.mongodb.controller;

import com.eagle.common.result.R;
import lombok.Data;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/mongodb")
class HealthController {

    @Resource
    private MongoTemplate mongoTemplate;

    @Data
    static
    class Student {
        public String name;
    }

    @GetMapping("/health")
    public R getHealth() {
        Criteria gt = Criteria
                .where("name").is("eagle");
        Query query = new Query(gt);
        HealthController.Student one = mongoTemplate.findOne(query, HealthController.Student.class);
        return R.ok().data("health", one);
    }
}
