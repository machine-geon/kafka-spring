package com.mingeon.configuration;

import java.util.HashMap;
import java.util.Map;

import com.mingeon.consumer.listener.DefaultMessageListener;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListenerConfigurer;
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.ContainerProperties.AckMode;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class MessageListenerContainerConfiguration implements KafkaListenerConfigurer{

    private final LocalValidatorFactoryBean validator;

    public MessageListenerContainerConfiguration(LocalValidatorFactoryBean validator) {
        this.validator = validator;
    }

    @Bean
    public KafkaMessageListenerContainer<String, String> kafkaMessageListenerContainer() {
        ContainerProperties containerProps = new ContainerProperties("clip4");
        containerProps.setGroupId("clip4-container");
        containerProps.setAckMode(AckMode.BATCH);
        containerProps.setMessageListener(new DefaultMessageListener());

        KafkaMessageListenerContainer<String, String> container = new KafkaMessageListenerContainer<>(containerFactory(), containerProps);
        container.setAutoStartup(false);

        return container;
    }

    private ConsumerFactory<String, String> containerFactory() {
        return new DefaultKafkaConsumerFactory<>(props());
    }

    private Map<String, Object> props() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return props;
    }

    @Override
    public void configureKafkaListeners(KafkaListenerEndpointRegistrar registrar) {
        registrar.setValidator(validator);
        
    }
}
