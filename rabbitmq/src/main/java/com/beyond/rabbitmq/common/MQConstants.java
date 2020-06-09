package com.beyond.rabbitmq.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2019/10/17
 */
@Configuration
public class MQConstants {

    public static final String EXCHANGE_DIRECT = "test.direct";
    public static final String EXCHANGE_FANOUT = "test.fanout";
    public static final String EXCHANGE_TOPIC = "test.topic";
    public static final String EXCHANGE_HEADER = "test.header";

    public static final String QUEUE_DIRECT = "direct.queue";
    public static final String QUEUE_FANOUT_ONE = "fanout.queue.one";
    public static final String QUEUE_FANOUT_TWO = "fanout.queue.two";
    public static final String QUEUE_TOPIC_NEWS = "topic.news";
    public static final String QUEUE_TOPIC_WEATHER = "topic.weather";
    public static final String QUEUE_HEADER = "header.queue";


    @Bean
    public Exchange directExchange() {
        return ExchangeBuilder.directExchange(EXCHANGE_DIRECT).build();
    }

    @Bean
    public Exchange fanoutExchange() {
        return ExchangeBuilder.fanoutExchange(EXCHANGE_FANOUT).build();
    }

    @Bean
    public Exchange topicExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPIC).build();
    }

    @Bean
    public HeadersExchange headerExchange() {
        return ExchangeBuilder.headersExchange(EXCHANGE_HEADER).build();
    }

    @Bean
    public Queue directQueue() {
        return QueueBuilder.durable(QUEUE_DIRECT).build();
    }

    @Bean
    public Queue fanoutQueueOne() {
        return QueueBuilder.durable(QUEUE_FANOUT_ONE).build();
    }

    @Bean
    public Queue fanoutQueueTwo() {
        return QueueBuilder.durable(QUEUE_FANOUT_TWO).build();
    }

    @Bean
    public Queue topicQueueNews() {
        return QueueBuilder.durable(QUEUE_TOPIC_NEWS).build();
    }

    @Bean
    public Queue topicQueueWeather() {
        return QueueBuilder.durable(QUEUE_TOPIC_WEATHER).build();
    }

    @Bean
    public Queue headerQueue() {
        return QueueBuilder.durable(QUEUE_HEADER).build();
    }

    @Bean
    public Binding bindingDirectExchange(Queue directQueue, Exchange directExchange) {
        return BindingBuilder.bind(directQueue).to(directExchange).with("test").noargs();
    }

    @Bean
    public Binding bindingFanoutExchangeOne(Queue fanoutQueueOne, Exchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueueOne).to(fanoutExchange).with("fanout.one").noargs();
    }

    @Bean
    public Binding bindingFanoutExchangeTwo(Queue fanoutQueueTwo, Exchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueueTwo).to(fanoutExchange).with("fanout.two").noargs();
    }

    @Bean
    public Binding bindingTopicExchangeNews(Queue topicQueueNews, Exchange topicExchange) {
        return BindingBuilder.bind(topicQueueNews).to(topicExchange).with("#.news").noargs();
    }

    @Bean
    public Binding bindingTopicExchangeWeather(Queue topicQueueWeather, Exchange topicExchange) {
        return BindingBuilder.bind(topicQueueWeather).to(topicExchange).with("weather.#").noargs();
    }

    @Bean
    public Binding bindingHeaderExchange(Queue headerQueue, HeadersExchange headerExchange) {
        Map<String, Object> header = new HashMap<>();
        header.put("last-name", "sweet");
        header.put("first-name", "lucifer");
        return BindingBuilder.bind(headerQueue).to(headerExchange).whereAny(header).match();
    }

}
