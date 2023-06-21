package com.eagle.mongodb.dao.impl;

import com.eagle.mongodb.dao.GavDao;
import com.eagle.mongodb.data.GavRepository;
import com.eagle.mongodb.entity.Gav;
import com.eagle.mongodb.tacos.Ingredient;
import com.eagle.mongodb.tacos.Taco;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
@Component
public class GavDaoImpl implements GavDao {
    public static String COL_GAV = "GAV";
    @Resource
    private GavRepository gavRepository;

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void saveGav(Gav gav) {

        gavRepository.save(gav);
    }

    @Override
    public void saveOne(Gav gav) {
        mongoTemplate.save(gav, COL_GAV);
    }

}
