package org.spring.mongodb.example.mongodb.controller;

import org.spring.mongodb.example.mongodb.service.TacoService;
import org.spring.mongodb.example.mongodb.service.IngredientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/taco")
public class AController {
    @Resource
    private TacoService tacoService;

    @Resource
    private IngredientService ingredientService;

    @GetMapping("/add")
    public String add() {
        // http://localhost:8001/taco/add
        tacoService.add();
        return "Ok";
    }

    @GetMapping("/addIngredient")
    public String addIngredient() {
        return ingredientService.add().toString();

    }
}