package com.eagle.mongodb.data;

import com.eagle.mongodb.tacos.TacoOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
