package org.spring.mongodb.example.mysql.controller;


import org.spring.mongodb.example.common.result.R;
import org.spring.mongodb.example.mysql.pojo.entity.Health;
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
    public R getHealth() {
        return R.builder().build().httpStatus(HttpStatus.OK);
    }
    @PostMapping("/healthJson")
    public R postHealthJson(@RequestBody Health health) {
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

