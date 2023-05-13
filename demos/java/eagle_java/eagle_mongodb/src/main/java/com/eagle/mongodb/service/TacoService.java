package com.eagle.mongodb.service;

import com.eagle.mongodb.data.TacoRepository;
import com.eagle.mongodb.tacos.Ingredient;
import com.eagle.mongodb.tacos.Taco;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

@Service
public class TacoService {
    @Resource
    private TacoRepository tacoRepository;

    public void add() {
        Taco taco = new Taco();
        taco.setCreatedAt(new Date());
        taco.setName("tmp1212");
        taco.addIngredient(new Ingredient(null, "tmp_name", Ingredient.Type.CHEESE));
        System.out.println(tacoRepository.save(taco));
    }
}
