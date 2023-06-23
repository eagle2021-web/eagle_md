package org.spring.mongodb.example.mongodb.service;

import org.spring.mongodb.example.mongodb.tacos.Ingredient;
import org.spring.mongodb.example.mongodb.data.IngredientRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IngredientService {

    @Resource
    private IngredientRepository ingredientRepository;

    public Ingredient add() {
        return ingredientRepository.save(new Ingredient(null, "one_ingredient", Ingredient.Type.CHEESE));
    }
}
