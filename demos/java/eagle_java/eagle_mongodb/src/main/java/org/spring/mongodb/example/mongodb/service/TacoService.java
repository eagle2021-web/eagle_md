package org.spring.mongodb.example.mongodb.service;

import org.spring.mongodb.example.mongodb.data.TacoRepository;
import org.spring.mongodb.example.mongodb.tacos.Ingredient;
import org.spring.mongodb.example.mongodb.tacos.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
@Slf4j
@Service
public class TacoService {
    @Resource
    private TacoRepository tacoRepository;

    public void add() {
        Taco taco = new Taco();
        taco.setId("5492dd76b9501637d05d8cd12120");
        taco.setCreatedAt(new Date());
        taco.setName("111111");
        taco.addIngredient(new Ingredient(null, "tmp_name", Ingredient.Type.CHEESE));
        log.info(tacoRepository.save(taco).toString());
    }
}
