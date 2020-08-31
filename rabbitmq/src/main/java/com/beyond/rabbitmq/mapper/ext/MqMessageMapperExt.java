package com.beyond.rabbitmq.mapper.ext;

import com.beyond.rabbitmq.entity.MqMessage;
import com.beyond.rabbitmq.mapper.MqMessageMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author lucifer
 * @date 2020/8/28 14:27
 */
public interface MqMessageMapperExt extends MqMessageMapper {

    MqMessage selectRecordByMessageId(@Param("messageId") String messageId);
}
