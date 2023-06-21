package com.eagle.mongodb.data;

import com.eagle.mongodb.entity.Gav;
import com.eagle.mongodb.tacos.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface GavRepository extends CrudRepository<Gav, Long> {

}
