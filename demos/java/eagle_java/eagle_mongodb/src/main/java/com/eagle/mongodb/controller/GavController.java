package com.eagle.mongodb.controller;

import com.eagle.common.result.ResultBean;
import com.eagle.mongodb.data.GavRepository;
import com.eagle.mongodb.entity.Gav;
import com.eagle.mongodb.service.GavService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
@Slf4j
@RestController
@RequestMapping("/gav")
public class GavController {
    @Resource
    private GavService gavService;

    @PostMapping("/saveGav")
    public ResultBean<String> saveGav(@RequestBody Gav gav) {
        log.info(gav.toString());
        gavService.saveGav(gav);
        return ResultBean.getInstance(gav.getUid());
    }

    @PostMapping("/saveOne")
    public ResultBean<String> saveOne(@RequestBody Gav gav) {
        log.info(gav.toString());
        gavService.saveOne(gav);
        return ResultBean.getInstance(gav.getUid());
    }
}
