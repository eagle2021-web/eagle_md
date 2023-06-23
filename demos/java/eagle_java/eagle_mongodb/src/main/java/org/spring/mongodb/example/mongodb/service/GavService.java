package org.spring.mongodb.example.mongodb.service;

import org.spring.mongodb.example.mongodb.entity.Gav;

public interface GavService {
    void saveGav(Gav gav);
    void saveOne(Gav gav);
}
