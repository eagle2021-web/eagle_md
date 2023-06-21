package com.eagle.mongodb.service;

import com.eagle.mongodb.entity.Gav;

public interface GavService {
    void saveGav(Gav gav);
    void saveOne(Gav gav);
}
