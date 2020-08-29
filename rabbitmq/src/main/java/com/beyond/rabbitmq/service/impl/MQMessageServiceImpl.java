package com.beyond.rabbitmq.service.impl;

import com.beyond.rabbitmq.entity.MqMessageWithBLOBs;
import com.beyond.rabbitmq.json.JsonUtils;
import com.beyond.rabbitmq.mapper.ext.MqMessageMapperExt;
import com.beyond.rabbitmq.service.MQMessageService;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

/**
 * @author lucifer
 * @date 2020/8/28 14:59
 */
@Service
public class MQMessageServiceImpl implements MQMessageService {

    private final MqMessageMapperExt mqMessageMapperExt;

    public MQMessageServiceImpl(final MqMessageMapperExt mqMessageMapperExt) {this.mqMessageMapperExt = mqMessageMapperExt;}

    @Override
    public boolean hasConsumedMessage(final String messageId) {
        return false;
    }

    @Override
    public void saveMessage(final Message message) {
        MqMessageWithBLOBs record = new MqMessageWithBLOBs();
        record.setMessageId(message.getMessageProperties().getMessageId());
        record.setMessageProperties(message.getMessageProperties().toString());
        record.setMessageBody(JsonUtils.serialize(message.getBody()));

        mqMessageMapperExt.insertSelective(record);
    }
}
