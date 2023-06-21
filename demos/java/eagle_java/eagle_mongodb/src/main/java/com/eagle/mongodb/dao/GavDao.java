package com.eagle.mongodb.dao;

import com.eagle.mongodb.entity.Gav;

public interface GavDao {
    void saveGav(Gav gav);
    void saveOne(Gav gav);
}
