package com.eagle.mongodb.service.impl;

import com.eagle.mongodb.dao.GavDao;
import com.eagle.mongodb.dao.impl.GavDaoImpl;
import com.eagle.mongodb.entity.Gav;
import com.eagle.mongodb.service.GavService;
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
