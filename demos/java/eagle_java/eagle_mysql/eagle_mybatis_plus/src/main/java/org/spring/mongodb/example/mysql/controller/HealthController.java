package org.spring.mongodb.example.mysql.controller;


import org.spring.mongodb.example.mysql.pojo.entity.Health;
import org.spring.mongodb.example.mysql.pojo.entity.R;
import org.spring.mongodb.example.mysql.service.HealthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author eagle
 * @since 2022-11-15
 */
@RestController
@RequestMapping("/health")

public class HealthController {
    @Resource
    private HealthService healthService;

    @GetMapping("")
    public R getHealth() {
        // http://localhost:8112/mysqlPlus/health
        return R.builder().build().httpStatus(HttpStatus.OK);
    }

    @PostMapping("/healthJson")
    public R postHealthJson(@RequestBody Health health) {
        return R.builder()
                .data(health)
                .build()
                .httpStatus(HttpStatus.OK);
    }

    @GetMapping("/getOneById")
    public R getOneById(Integer id) {
        Health health = healthService.getHealthById(id);
        return R.builder().data(health).build().httpStatus(HttpStatus.OK);
    }
}

