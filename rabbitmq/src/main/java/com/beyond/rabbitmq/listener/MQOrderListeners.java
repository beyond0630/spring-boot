package com.beyond.rabbitmq.listener;

import java.io.IOException;
import java.util.Map;

import com.beyond.rabbitmq.common.MQOrderConstants;
import com.beyond.rabbitmq.model.Order;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author hdb001
 * @date 2020/7/4 11:28
 */
@Component
public class MQOrderListeners {

    private static final Logger LOGGER = LoggerFactory.getLogger(MQOrderListeners.class);

    @RabbitListener(queues = MQOrderConstants.QUEUE_ORDER)
    public void handlerOrder(@Payload Order order, Channel channel,
                             @Header(value = "amqp_deliveryTag") long deliveryTag) throws IOException {
        if (order.getId() % 3 == 0) {
            channel.basicNack(deliveryTag, false, false);
        }
        LOGGER.debug("[{}] queue receive message: {}", MQOrderConstants.QUEUE_ORDER, order);
    }

    @RabbitListener(queues = MQOrderConstants.QUEUE_ORDER_DEAD)
    public void handlerDeadOrder(@Payload Order order) {
        LOGGER.debug("[{}] queue receive message: {}", MQOrderConstants.QUEUE_ORDER_DEAD, order);
    }

}
