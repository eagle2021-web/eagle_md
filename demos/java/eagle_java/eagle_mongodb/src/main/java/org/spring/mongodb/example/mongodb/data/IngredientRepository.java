package org.spring.mongodb.example.mongodb.data;

import org.spring.mongodb.example.mongodb.tacos.Ingredient;
import org.springframework.data.repository.CrudRepository;


public interface IngredientRepository
        extends CrudRepository<Ingredient, String>{

}
