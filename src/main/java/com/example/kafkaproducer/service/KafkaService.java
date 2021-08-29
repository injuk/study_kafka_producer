package com.example.kafkaproducer.service;

import com.example.kafkaproducer.config.KafkaConfigs;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Log4j2
@Service
public class KafkaService {

    private final KafkaConfigs kafkaConfigs;

    public KafkaService(KafkaConfigs kafkaConfigs) {
        this.kafkaConfigs = kafkaConfigs;
    }

    @PostConstruct
    public void init() {
        log.info("topic: " + kafkaConfigs.getTopic());
        log.info("url: " + kafkaConfigs.getBootstrap());
    }

    public String getHello(String name) {
        return "Hello, " + name + "?";
    }

    public void runKafka() {
        String url = kafkaConfigs.getBootstrap();
        String topic = kafkaConfigs.getTopic();
        Properties configs = new Properties();

        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, url);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(configs);

        String messageValue = "testMessage";
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, messageValue);

        producer.send(record);
        log.info("{}", record);
        producer.flush();
        producer.close();
    }
}
