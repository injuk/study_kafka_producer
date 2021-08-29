package com.example.kafkaproducer.controller;

import com.example.kafkaproducer.service.KafkaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping(value = "")
public class KafkaController {

    private final KafkaService kafkaService;

    public KafkaController(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @GetMapping("/hello")
    public String injuk() {
        log.info("who's that?");
        return kafkaService.getHello("injuk");
    }

    @GetMapping("/produce")
    public void produce() {
        log.info("kafka procuder");
        kafkaService.runKafka();
    }
}
