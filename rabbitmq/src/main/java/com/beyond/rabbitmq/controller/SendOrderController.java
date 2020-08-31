package com.beyond.rabbitmq.controller;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import com.beyond.rabbitmq.common.MQOrderConstants;
import com.beyond.rabbitmq.entity.MqOrder;
import com.beyond.rabbitmq.json.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
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
        MqOrder order = new MqOrder();
        order.setId(key);
        order.setName("order: " + key);

        Message message = MessageBuilder.withBody(JsonUtils.serialize(order).getBytes(StandardCharsets.UTF_8))
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setMessageId(UUID.randomUUID().toString())
                .build();
        amqpTemplate.send(MQOrderConstants.EXCHANGE_ORDER, MQOrderConstants.KEY_ORDER, message);
        LOGGER.debug("sent message to [{}] with key [{}]", MQOrderConstants.EXCHANGE_ORDER, MQOrderConstants.KEY_ORDER);
        return "success";
    }
}
