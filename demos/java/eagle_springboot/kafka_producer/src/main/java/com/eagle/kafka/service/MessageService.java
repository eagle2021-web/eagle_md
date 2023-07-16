package com.eagle.kafka.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class MessageService {

    public static final String TOPIC = "gen";
    @Resource
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public MessageService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produce(String key, String message) {
        if (Objects.nonNull(key)) {
            // 发送消息
            this.kafkaTemplate.send(TOPIC, key, message);
            System.out.println(1111111111);
        } else {
            // 发送不带key的消息
            this.kafkaTemplate.send(TOPIC, message);
        }
    }
}
