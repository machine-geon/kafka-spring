package com.mingeon.configuration;

import java.util.HashMap;
import java.util.Map;

import com.mingeon.model.Animal;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaJsonTemplateConfiguration {
    
    @Bean
    public KafkaTemplate<String, Animal> kafkaJsonTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    private ProducerFactory<String, Animal> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerProp());
    }

    private Map<String, Object> producerProp() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return props;
    }
}
