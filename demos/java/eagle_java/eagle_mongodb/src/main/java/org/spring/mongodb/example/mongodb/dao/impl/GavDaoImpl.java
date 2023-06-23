package org.spring.mongodb.example.mongodb.dao.impl;

import org.spring.mongodb.example.mongodb.dao.GavDao;
import org.spring.mongodb.example.mongodb.data.GavRepository;
import org.spring.mongodb.example.mongodb.entity.Gav;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class GavDaoImpl implements GavDao {
    public static String COL_GAV = "GAV222";
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

    @Override
    public void saveByGav(Gav gav) {

    }


}
