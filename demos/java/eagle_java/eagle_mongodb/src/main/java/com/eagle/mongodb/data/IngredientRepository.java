package com.eagle.mongodb.data;

import org.springframework.data.repository.CrudRepository;
import com.eagle.mongodb.tacos.Ingredient;
import org.springframework.stereotype.Component;


public interface IngredientRepository
        extends CrudRepository<Ingredient, String>{

}
