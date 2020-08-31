package com.beyond.rabbitmq.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import com.beyond.rabbitmq.entity.MqMessage;
import com.beyond.rabbitmq.entity.MqMessageWithBLOBs;
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
        MqMessage record = mqMessageMapperExt.selectRecordByMessageId(messageId);
        return Objects.nonNull(record);
    }

    @Override
    public void saveMessage(final Message message) {
        MqMessageWithBLOBs record = new MqMessageWithBLOBs();
        record.setMessageId(message.getMessageProperties().getMessageId());
        record.setMessageProperties(message.getMessageProperties().toString());
        record.setMessageBody(new String(message.getBody(), StandardCharsets.UTF_8));
        mqMessageMapperExt.insertSelective(record);
    }
}
