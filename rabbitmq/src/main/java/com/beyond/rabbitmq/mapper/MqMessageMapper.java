package com.beyond.rabbitmq.mapper;

import com.beyond.rabbitmq.entity.MqMessage;
import com.beyond.rabbitmq.entity.MqMessageWithBLOBs;

public interface MqMessageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mq_message
     *
     * @mbg.generated Fri Aug 28 14:28:31 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mq_message
     *
     * @mbg.generated Fri Aug 28 14:28:31 CST 2020
     */
    int insert(MqMessageWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mq_message
     *
     * @mbg.generated Fri Aug 28 14:28:31 CST 2020
     */
    int insertSelective(MqMessageWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mq_message
     *
     * @mbg.generated Fri Aug 28 14:28:31 CST 2020
     */
    MqMessageWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mq_message
     *
     * @mbg.generated Fri Aug 28 14:28:31 CST 2020
     */
    int updateByPrimaryKeySelective(MqMessageWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mq_message
     *
     * @mbg.generated Fri Aug 28 14:28:31 CST 2020
     */
    int updateByPrimaryKeyWithBLOBs(MqMessageWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mq_message
     *
     * @mbg.generated Fri Aug 28 14:28:31 CST 2020
     */
    int updateByPrimaryKey(MqMessage record);
}