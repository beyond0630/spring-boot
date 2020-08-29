package com.beyond.rabbitmq.service;

import com.beyond.rabbitmq.entity.MqOrder;

/**
 * @author lucifer
 * @date 2020/8/28 14:35
 */
public interface OrderService {

    void saveOrder(MqOrder order);
}
