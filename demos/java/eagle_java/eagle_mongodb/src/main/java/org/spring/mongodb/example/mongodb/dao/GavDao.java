package org.spring.mongodb.example.mongodb.dao;

import org.spring.mongodb.example.mongodb.entity.Gav;

public interface GavDao {
    void saveGav(Gav gav);
    void saveOne(Gav gav);
    void saveByGav(Gav gav);
}
