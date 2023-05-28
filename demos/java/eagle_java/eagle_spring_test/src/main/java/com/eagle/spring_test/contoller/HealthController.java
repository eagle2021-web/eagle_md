package com.eagle.spring_test.contoller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")

public class HealthController {
//    @Resource
//    private HealthService healthService;

    @GetMapping("")
    public String getHealth() {
        return "ok";
    }
}
