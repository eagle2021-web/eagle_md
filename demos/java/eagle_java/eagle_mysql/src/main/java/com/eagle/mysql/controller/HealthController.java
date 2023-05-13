package com.eagle.mysql.controller;


import com.eagle.common.result.R;
import com.eagle.mysql.pojo.entity.Health;
import com.eagle.mysql.service.HealthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    @Resource
    private HealthService healthService;

    @GetMapping("")
    public R getHealth() {
        return R.ok().data("health", "ok");
    }

    @GetMapping("/one")
    public R getOne() {
        Health health = healthService.getById(1);
        return R.ok().data("health", health);
    }
}

