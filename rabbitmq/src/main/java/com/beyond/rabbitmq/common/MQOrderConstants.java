package com.beyond.rabbitmq.common;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hdb001
 * @date 2020/7/4 11:18
 */
@Configuration
public class MQOrderConstants {

    public static final String EXCHANGE_ORDER = "lucifer.order";
    public static final String EXCHANGE_ORDER_DEAD = "lucifer.order.dead";

    public static final String QUEUE_ORDER = "lucifer.order";
    public static final String QUEUE_ORDER_DEAD = "lucifer.order.dead";

    public static final String KEY_ORDER = "order";
    public static final String KEY_ORDER_DEAD = "order.dead";


    @Bean
    public Exchange orderExchange() {
        return new DirectExchange(EXCHANGE_ORDER);
    }

    @Bean
    public Queue orderQueue() {
        return QueueBuilder.durable(QUEUE_ORDER)
                .deadLetterExchange(EXCHANGE_ORDER_DEAD)
                .deadLetterRoutingKey(KEY_ORDER_DEAD)
                .build();
    }

    @Bean
    public Binding orderBinding(Exchange orderExchange, Queue orderQueue) {
        return BindingBuilder.bind(orderQueue).to(orderExchange).with(KEY_ORDER).noargs();
    }

    @Bean
    public Exchange orderDeadExchange() {
        return new DirectExchange(QUEUE_ORDER_DEAD);
    }

    @Bean
    public Queue orderDeadQueue() {
        return QueueBuilder.durable(QUEUE_ORDER_DEAD).build();
    }

    @Bean
    public Binding orderDeadBinding(Exchange orderDeadExchange, Queue orderDeadQueue) {
        return BindingBuilder.bind(orderDeadQueue).to(orderDeadExchange).with(KEY_ORDER_DEAD).noargs();
    }
}
