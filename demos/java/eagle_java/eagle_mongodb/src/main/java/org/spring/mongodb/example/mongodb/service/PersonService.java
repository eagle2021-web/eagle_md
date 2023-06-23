package org.spring.mongodb.example.mongodb.service;

import org.spring.mongodb.example.mongodb.entity.Person;

public interface PersonService {
    void upsert(Person person);
}
