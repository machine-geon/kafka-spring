package com.mingeon.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin.NewTopics;

@Configuration
public class KafkaTopicConfiguration {

    @Bean
    public NewTopics clip2s() {
        return new NewTopics(
                TopicBuilder.name("clip3").build(),
                TopicBuilder.name("clip3-bytesid").build(),
                TopicBuilder.name("clip3-request").build(),
                TopicBuilder.name("clip3-replies").build()
        );
    }
}
