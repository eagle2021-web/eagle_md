package com.eagle.mongodb.controller;

import com.eagle.mongodb.service.TacoService;
import com.eagle.mongodb.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/taco")
public class AController {
    @Autowired
    private TacoService tacoService;

    @Autowired
    private IngredientService ingredientService;
    @GetMapping("/add")
    public String add() {
        tacoService.add();
        return "Ok";
    }

    @GetMapping("/addIngredient")
    public String addIngredient() {
        return ingredientService.add().toString();

    }
}
