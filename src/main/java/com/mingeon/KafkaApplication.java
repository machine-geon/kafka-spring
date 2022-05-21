package com.mingeon;

import java.nio.charset.StandardCharsets;

import com.mingeon.producer.ClipProducer;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(ClipProducer clipProducer) {
        return args -> {
            clipProducer.async("clip3", "Hello, clip3-async");
            clipProducer.sync("clip3", "Hello, clip3-sync");
            clipProducer.routingSend("clip3", "Hello, clip3-routing");
            clipProducer.routingSendBytes("clip3-bytes", "Hello, clip3-bytes".getBytes(StandardCharsets.UTF_8));
            clipProducer.replyingSend("clip3-request", "Ping Clip3");
        };
    }
}
