package com.mingeon.service;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeConfigsResult;
import org.apache.kafka.common.config.ConfigResource;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

@Service
public class KafkaManager {
    private final KafkaAdmin kafkaAdmin;
    private final AdminClient adminClient;
    
    public KafkaManager(KafkaAdmin kafkaAdmin) {
        this.kafkaAdmin = kafkaAdmin;
        this.adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());
    }

    public void describeTopicConfigs() throws InterruptedException, ExecutionException {
        Collection<ConfigResource> resources = List.of(
            new ConfigResource(ConfigResource.Type.TOPIC, "clip4-animal")
        );

        DescribeConfigsResult result = adminClient.describeConfigs(resources);
        System.out.println(result.all().get());
    }
    
}
