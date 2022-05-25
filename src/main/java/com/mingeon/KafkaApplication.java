package com.mingeon;

import com.mingeon.model.Animal;
import com.mingeon.producer.ClipProducer;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;

@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(ClipProducer clipProducer,
            KafkaMessageListenerContainer<String, String> kafkaMessageListenerContainer) {
        return args -> {
            clipProducer.async("clip4-animal", new Animal("puppy", 10));
        };
    }
}
