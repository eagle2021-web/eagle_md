package org.spring.mongodb.example.mongodb.data;

import org.spring.mongodb.example.mongodb.tacos.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
