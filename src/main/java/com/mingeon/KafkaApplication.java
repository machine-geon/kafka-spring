package com.mingeon;

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
    public ApplicationRunner runner(ClipProducer clipProducer, KafkaMessageListenerContainer<String, String> kafkaMessageListenerContainer) {
        return args -> {
            clipProducer.async("clip4", "Hello, clip4 Container.");
            kafkaMessageListenerContainer.start();
            Thread.sleep(1000L);

            System.out.println("-- pause --");
            kafkaMessageListenerContainer.pause();
            Thread.sleep(5000L);

            clipProducer.async("clip4", "Hello, Secondly clip4 Container.");

            System.out.println("-- resume --");
            kafkaMessageListenerContainer.resume();
            Thread.sleep(1000L);

            System.out.println("-- stop--");
            kafkaMessageListenerContainer.stop();
        };
    }
}
