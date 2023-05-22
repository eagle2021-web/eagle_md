package com.eagle.core.controller;

import com.eagle.common.result.R;
import com.eagle.service.HelloService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

    @Resource
    @Qualifier("helloService")
    private HelloService helloService;
    @GetMapping("/health")
    public R Health() {
        helloService.sayHello();
        System.out.println("----------");
        return R.builder().build().ok();
    }
    @GetMapping("/notFound")
    public ResponseEntity notFound() {
        System.out.println("----------");
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("dsfasdf")
                ;
    }

}
