package com.eagle.mongodb.service;

import com.eagle.mongodb.entity.Person;

public interface PersonService {
    void upsert(Person person);
}
