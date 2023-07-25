package org.spring.mongodb.example.spring_test.contoller;

import org.spring.mongodb.example.service.SService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/health")

public class HealthController {
//    @Resource
//    private HealthService healthService;
    @Resource
    private SService sService;
    @GetMapping("/one")
    public String getHealth() {
        System.out.println("adfsdf");
        return sService.abc("sfa");
    }

    @PostMapping("/two")
    public String two() {
        return "ok";
    }
}
