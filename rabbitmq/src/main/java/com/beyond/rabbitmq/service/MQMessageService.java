package com.beyond.rabbitmq.service;

import org.springframework.amqp.core.Message;

/**
 * @author lucifer
 * @date 2020/8/28 14:50
 */
public interface MQMessageService {

    boolean hasConsumedMessage(String messageId);

    void saveMessage(Message message);
}
