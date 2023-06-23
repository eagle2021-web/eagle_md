package org.spring.mongodb.example.mongodb.data;

import org.spring.mongodb.example.mongodb.entity.Gav;
import org.springframework.data.repository.CrudRepository;

public interface GavRepository extends CrudRepository<Gav, Long> {

}
