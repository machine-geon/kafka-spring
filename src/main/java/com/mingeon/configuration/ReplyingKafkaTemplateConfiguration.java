package com.mingeon.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
public class ReplyingKafkaTemplateConfiguration {
    
    @Bean
    public ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate(ProducerFactory<String, String> producerFactory, ConcurrentMessageListenerContainer<String, String> repliesContatiner) {
        return new ReplyingKafkaTemplate<>(producerFactory, repliesContatiner);
    }

    @Bean
    public ConcurrentMessageListenerContainer<String, String> repliesContatiner(ConcurrentKafkaListenerContainerFactory<String, String> containerFactory) {
        ConcurrentMessageListenerContainer<String, String> container = containerFactory.createContainer("clip3-replies");
        container.getContainerProperties().setGroupId("clip3-replies-container-id");
        
        return container;
    }
}
