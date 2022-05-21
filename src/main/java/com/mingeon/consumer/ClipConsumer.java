package com.mingeon.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class ClipConsumer {

    @KafkaListener(id = "clip3-id", topics = "clip3")
    public void listenClip3(String message) {
        System.out.println(message);
    }

    @KafkaListener(id = "clip3-bytesid", topics = "clip3-bytes")
    public void listenClip3Bytes(String message) {
        System.out.println(message);
    }

    @KafkaListener(id = "clip3-request-id", topics = "clip3-bytes")
    @SendTo
    public String listenClipRequest(String message) {
        System.out.println(message);
        return "Pong clip3";
    }
}
