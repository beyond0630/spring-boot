package com.beyond.rabbitmq.listener;

import com.beyond.rabbitmq.common.MQConstants;
import com.beyond.rabbitmq.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2019/10/17
 */
@Component
public class RabbitListeners {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitListeners.class);

    @RabbitListener(queues = MQConstants.QUEUE_DIRECT)
    public void handlerDirect(@Payload User user) {
        LOGGER.debug("[{}] queue receive message: {}", MQConstants.QUEUE_DIRECT, user);
    }

    @RabbitListener(queues = MQConstants.QUEUE_FANOUT_ONE)
    public void handlerFanoutOne(@Payload User user) {
        LOGGER.debug("[{}] queue receive message: {}", MQConstants.QUEUE_FANOUT_ONE, user);
    }

    @RabbitListener(queues = MQConstants.QUEUE_FANOUT_TWO)
    public void handlerFanoutTwo(@Payload User user) {
        LOGGER.debug("[{}] queue receive message: {}", MQConstants.QUEUE_FANOUT_TWO, user);
    }

    @RabbitListener(queues = MQConstants.QUEUE_TOPIC_NEWS)
    public void handlerTopicNews(@Payload User user) {
        LOGGER.debug("[{}] queue receive message: {}", MQConstants.QUEUE_TOPIC_NEWS, user);
    }

    @RabbitListener(queues = MQConstants.QUEUE_TOPIC_WEATHER)
    public void handlerTopicWeather(@Payload User user) {
        LOGGER.debug("[{}] queue receive message: {}", MQConstants.QUEUE_TOPIC_WEATHER, user);
    }

    @RabbitListener(queues = MQConstants.QUEUE_HEADER)
    public void handlerHeader(@Payload User user) {
        LOGGER.debug("[{}] queue receive message: {}", MQConstants.QUEUE_HEADER, user);
    }

}
