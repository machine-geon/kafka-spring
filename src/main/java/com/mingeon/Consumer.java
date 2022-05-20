package com.mingeon;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @KafkaListener(id = "mingeon", topics = "quickstart-events")
    public void listen(String message) {
        System.out.println("===========");
        System.out.println(message);
        System.out.println("===========");
    }
}
