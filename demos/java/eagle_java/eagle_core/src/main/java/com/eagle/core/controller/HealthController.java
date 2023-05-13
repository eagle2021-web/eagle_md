package com.eagle.core.controller;

import com.eagle.common.result.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public R Health() {
        return R.ok();
    }
}
