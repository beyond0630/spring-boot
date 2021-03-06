package com.beyond.rabbitmq.controller;

import com.beyond.rabbitmq.common.MQConstants;
import com.beyond.rabbitmq.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2019/10/17
 */
@RestController
@RequestMapping("/send")
public class SendController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendController.class);

    private final AmqpTemplate amqpTemplate;

    public SendController(AmqpTemplate amqpTemplate) {this.amqpTemplate = amqpTemplate;}

    /**
     * 发送 MQ 消息
     */
    @GetMapping("direct/{key}")
    public String sendDirectMessage(@PathVariable String key) {
        amqpTemplate.convertAndSend(MQConstants.EXCHANGE_DIRECT, key,
                User.newBuilder()
                        .withId(1)
                        .withName("lucifer")
                        .withEmail("lucifer email")
                        .build());
        LOGGER.debug("sent message to [{}] with key [{}]", MQConstants.EXCHANGE_DIRECT, key);
        return "success";
    }

    /**
     * 发送 MQ 消息
     */
    @GetMapping("fanout/{key}")
    public String sendFanoutMessage(@PathVariable String key) {
        amqpTemplate.convertAndSend(MQConstants.EXCHANGE_FANOUT, key,
                User.newBuilder()
                        .withId(1)
                        .withName("lucifer")
                        .withEmail("lucifer email")
                        .build());
        LOGGER.debug("sent message to [{}] with key [{}]", MQConstants.EXCHANGE_FANOUT, key);
        return "success";
    }

    /**
     * 发送 MQ 消息
     */
    @GetMapping("topic/{key}")
    public String sendTopicMessage(@PathVariable String key) {
        amqpTemplate.convertAndSend(MQConstants.EXCHANGE_TOPIC, key,
                User.newBuilder()
                        .withId(1)
                        .withName("lucifer")
                        .withEmail("lucifer email")
                        .build());
        LOGGER.debug("sent message to [{}] with key [{}]", MQConstants.EXCHANGE_TOPIC, key);
        return "success";
    }

    /**
     * 发送 MQ 消息
     */
    @GetMapping("header/{key}")
    public String sendHeaderMessage(@PathVariable int key) {
        User lucifer = User.newBuilder()
                .withId(1)
                .withName("lucifer")
                .build();

        MessagePostProcessor messagePostProcessor = message -> {
            message.getMessageProperties().setContentType("application/json");
            if (key == 1) {
                message.getMessageProperties().setHeader("last-name", "sweet");
                message.getMessageProperties().setHeader("first-name", "lucifer");
            } else if (key == 2) {
                message.getMessageProperties().setHeader("last-name", "sweet");
            } else {
                message.getMessageProperties().setHeader("last-name", "lucifer");
            }
            return message;
        };

        amqpTemplate.convertAndSend(MQConstants.EXCHANGE_HEADER, String.valueOf(key), lucifer, messagePostProcessor);
        LOGGER.debug("sent message to [{}] with key [{}]", MQConstants.EXCHANGE_HEADER, key);

        return "success";
    }
}
