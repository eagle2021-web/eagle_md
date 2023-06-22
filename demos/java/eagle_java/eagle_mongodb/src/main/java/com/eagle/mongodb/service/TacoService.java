package com.eagle.mongodb.service;

import com.eagle.mongodb.data.TacoRepository;
import com.eagle.mongodb.tacos.Ingredient;
import com.eagle.mongodb.tacos.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
