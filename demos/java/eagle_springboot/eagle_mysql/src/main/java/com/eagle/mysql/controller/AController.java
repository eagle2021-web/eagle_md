package com.eagle.mysql.controller;

import com.eagle.mysql.convertor.MapConvertor;
import com.eagle.mysql.pojo.dto.HealthDTO;
import com.eagle.mysql.pojo.entity.Health;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/abc")
public class AController {
//    @Resource
//    private MapConvertor mapConvertor;
    @GetMapping("/health")
    public void getHealth(){
        Health health = new Health();
        health.setId(1);
        health.setName("ads");
        health.setAge(11);
//        return mapConvertor.from(health);  // 修改为实际的返回值
    }
}
