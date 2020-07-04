package com.beyond.kafka.consumer.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author hdb001
 * @date 2020/6/29 16:02
 */
@Component
public class ConsumerListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerListener.class);

    @KafkaListener(topics = "testTopic")
    public void receiveMessage(String message){
        LOGGER.info("receive message {}", message);
    }
}
