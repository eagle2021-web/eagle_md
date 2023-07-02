package com.eagle.mysql.controller;


import com.eagle.mysql.pojo.entity.Health;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public String getHealth() {
        return "sdfsdf";
    }

//    @GetMapping("/one")
//    public R getOne() {
//        Health health = healthService.getById(1);
//        return R.builder().build().httpStatus(HttpStatus.OK);
//    }
}

