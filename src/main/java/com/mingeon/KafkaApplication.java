package com.mingeon;

import com.mingeon.service.KafkaManager;

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
    public ApplicationRunner runner(KafkaManager KafkaManager) {
        return args -> {
            KafkaManager.describeTopicConfigs();
        };
    }
}
