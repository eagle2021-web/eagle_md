package com.eagle.kafka.controller;


import com.eagle.kafka.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final MessageService messService;

    public HelloController(MessageService messService) {
        this.messService = messService;
    }

    @GetMapping("/produce/{key}/{message}")
    public String produce(@PathVariable String message, @PathVariable(required = false) String key) {
        messService.produce(key, message);
        return "发送消息1";
    }

    @GetMapping("/produce/{message}")
    public String produce(@PathVariable String message) {
        messService.produce(null, message);
        return "发送消息2";
    }
}