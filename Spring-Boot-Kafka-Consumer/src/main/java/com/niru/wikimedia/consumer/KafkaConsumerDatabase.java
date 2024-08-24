package com.niru.wikimedia.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerDatabase {
    private final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerDatabase.class);

    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(String eventMessage){
        LOGGER.info(String.format("Message received -> %s",eventMessage));
    }
}
