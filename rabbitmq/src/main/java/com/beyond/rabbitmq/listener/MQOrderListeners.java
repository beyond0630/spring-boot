package com.beyond.rabbitmq.listener;

import com.beyond.rabbitmq.annotation.MQMessageDurable;
import com.beyond.rabbitmq.common.MQOrderConstants;
import com.beyond.rabbitmq.entity.MqOrder;
import com.beyond.rabbitmq.json.JsonUtils;
import com.beyond.rabbitmq.service.OrderService;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hdb001
 * @date 2020/7/4 11:28
 */
@Component
public class MQOrderListeners {

    private static final Logger LOGGER = LoggerFactory.getLogger(MQOrderListeners.class);
    private final OrderService orderService;

    public MQOrderListeners(final OrderService orderService) {this.orderService = orderService;}

    @Transactional
    @MQMessageDurable
    @RabbitListener(queues = MQOrderConstants.QUEUE_ORDER)
    public void handlerOrder(Message message,
                             @Header(value = AmqpHeaders.RECEIVED_ROUTING_KEY) String key) {
        MqOrder order = JsonUtils.deserialize(message.getBody(), MqOrder.class);
        LOGGER.debug("[{}] queue receive message: key[{}], body[{}]", MQOrderConstants.QUEUE_ORDER, key, order);
        orderService.saveOrder(order);
    }

    @RabbitListener(queues = MQOrderConstants.QUEUE_ORDER_DEAD)
    public void handlerDeadOrder(Message message, Channel channel,
                                 @Header(value = "amqp_receivedRoutingKey") String key) {
        MqOrder order = JsonUtils.deserialize(message.getBody(), MqOrder.class);
        LOGGER.debug("[{}] queue receive message: key[{}], body[{}]", MQOrderConstants.QUEUE_ORDER_DEAD, key, order);
    }
}
