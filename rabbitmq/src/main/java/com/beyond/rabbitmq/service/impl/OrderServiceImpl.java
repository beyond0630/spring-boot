package com.beyond.rabbitmq.service.impl;

import com.beyond.rabbitmq.entity.MqOrder;
import com.beyond.rabbitmq.mapper.ext.MqOrderMapperExt;
import com.beyond.rabbitmq.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @author lucifer
 * @date 2020/8/28 14:36
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final MqOrderMapperExt orderMapperExt;

    public OrderServiceImpl(final MqOrderMapperExt orderMapperExt) {this.orderMapperExt = orderMapperExt;}

    @Override
    public void saveOrder(final MqOrder order) {
        orderMapperExt.insertSelective(order);
    }
}
