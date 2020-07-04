package com.beyond.rabbitmq.controller;

import com.beyond.rabbitmq.common.MQOrderConstants;
import com.beyond.rabbitmq.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
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
public class SendOrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendOrderController.class);

    private final AmqpTemplate amqpTemplate;

    public SendOrderController(AmqpTemplate amqpTemplate) {this.amqpTemplate = amqpTemplate;}

    /**
     * 发送 MQ 消息
     */
    @GetMapping("/order/{key}")
    public String sendDirectMessage(@PathVariable int key) {
        amqpTemplate.convertAndSend(MQOrderConstants.EXCHANGE_ORDER, MQOrderConstants.KEY_ORDER, new Order(key));
        LOGGER.debug("sent message to [{}] with key [{}]", MQOrderConstants.EXCHANGE_ORDER, MQOrderConstants.KEY_ORDER);
        return "success";
    }
}
