package com.eagle.mongodb.data;

import com.eagle.mongodb.tacos.Taco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
