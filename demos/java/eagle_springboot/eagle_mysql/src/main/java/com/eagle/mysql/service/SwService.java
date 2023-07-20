package com.eagle.mysql.service;

import org.bson.Document;

public interface SwService {
    void saveJson(String content);
    Document queryJson(String purl);
}
