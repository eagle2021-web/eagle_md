package com.eagle.mysql.controller;


import com.eagle.common.result.R;
import com.eagle.mysql.pojo.entity.Health;
import com.eagle.mysql.service.HealthService;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author eagle
 * @since 2022-11-15
 */
@RestController
@RequestMapping("/health")

public class HealthController {
//    @Resource
//    private HealthService healthService;

    @GetMapping("")
    public R getHealth() {
        return R.builder().build().httpStatus(HttpStatus.OK);
    }
    @PostMapping("/healthJson")
    public R postHealthJson(@RequestBody Health health) {
        System.out.println(health);
        return R.builder()
                .build()
                .addData("health", health)
                .httpStatus(HttpStatus.OK)
                ;
    }
//    @GetMapping("/one")
//    public R getOne() {
//        Health health = healthService.getById(1);
//        return R.builder().build().httpStatus(HttpStatus.OK);
//    }
}

