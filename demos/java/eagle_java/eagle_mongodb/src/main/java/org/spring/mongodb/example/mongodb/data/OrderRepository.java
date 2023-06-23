package org.spring.mongodb.example.mongodb.data;

import org.spring.mongodb.example.mongodb.tacos.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
