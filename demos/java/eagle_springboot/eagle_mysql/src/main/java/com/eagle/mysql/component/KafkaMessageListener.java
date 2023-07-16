package com.eagle.mysql.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaMessageListener {


    @KafkaListener(topics = "gen", groupId = "eagle")
    public void processMessage(ConsumerRecord<String, String> message) {
        System.out.println("从Test收到消息，其key为:" + message.key()
                + "，其value为:" + message.value());
    }

}