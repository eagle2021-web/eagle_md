package com.eagle.mongodb.service;

import com.eagle.mongodb.tacos.Ingredient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import com.eagle.mongodb.data.IngredientRepository;

@Service
public class IngredientService {

    @Resource
    private IngredientRepository ingredientRepository;

    public Ingredient add() {
        return ingredientRepository.save(new Ingredient(null, "one_ingredient", Ingredient.Type.CHEESE));
    }
}
