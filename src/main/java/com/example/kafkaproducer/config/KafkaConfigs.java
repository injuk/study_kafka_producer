package com.example.kafkaproducer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfigs {

    private final String TOPIC_NAME;
    private final String BOOTSTRAP_SERVERS;
    private final String PORT;

    public KafkaConfigs(@Value("${study.kafka.topic}") String TOPIC_NAME, @Value("${study.kafka.bootstrap}") String BOOTSTRAP_SERVERS, @Value("${study.kafka.port}") String PORT) {
        this.TOPIC_NAME = TOPIC_NAME;
        this.BOOTSTRAP_SERVERS = BOOTSTRAP_SERVERS;
        this.PORT = PORT;
    }

    public String getTopic() {
        return this.TOPIC_NAME;
    }
    public String getBootstrap() {
        return this.BOOTSTRAP_SERVERS + ":" + this.PORT;
    }

}
