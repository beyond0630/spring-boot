package com.beyond.rocketmq.consumer.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Beyond
 */
@Component
@RocketMQMessageListener(topic = "sync-test", consumerGroup = "sync-test-consumer")
public class SyncTestListener implements RocketMQListener<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SyncTestListener.class);

    @Override
    public void onMessage(final String message) {
        LOGGER.info("received message [{}]", message);

    }
}
