package org.spring.mongodb.example.mongodb.service.impl;

import org.spring.mongodb.example.mongodb.dao.impl.GavDaoImpl;
import org.spring.mongodb.example.mongodb.entity.Gav;
import org.spring.mongodb.example.mongodb.service.GavService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GavServiceImpl implements GavService {
    @Resource
    private GavDaoImpl gavDao;

    @Override
    public void saveGav(Gav gav) {
        gavDao.saveGav(gav);
    }

    @Override
    public void saveOne(Gav gav) {
        gavDao.saveOne(gav);
    }
}
