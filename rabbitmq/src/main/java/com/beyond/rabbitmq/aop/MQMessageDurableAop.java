package com.beyond.rabbitmq.aop;

import java.util.Objects;

import com.beyond.rabbitmq.service.MQMessageService;
import com.rabbitmq.client.Channel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

/**
 * @author lucifer
 * @date 2020/8/28 14:40
 */
@Aspect
@Component
public class MQMessageDurableAop {

    private static final Logger LOGGER = LoggerFactory.getLogger(MQMessageDurableAop.class);

    private final MQMessageService mqMessageService;

    public MQMessageDurableAop(final MQMessageService mqMessageService) {this.mqMessageService = mqMessageService;}

    @Pointcut("@annotation(org.springframework.amqp.rabbit.annotation.RabbitListener)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object doLock(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        Message message = getMessage(args);
        //
//        boolean b = mqMessageService.hasConsumedMessage(message.getMessageProperties().getMessageId());
        LOGGER.debug("args: [{}]", args);
        // 执行业务
        Object result = point.proceed();
        LOGGER.debug("result: [{}]", result);
        // 保存消息到数据库
        mqMessageService.saveMessage(message);
        Channel channel = getChannel(args);
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, false);
        return result;
    }

    private Message getMessage(final Object[] args) {
        Message message = null;
        for (Object arg : args) {
            if (arg instanceof Message) {
                message = (Message) arg;
                break;
            }
        }
        if (Objects.isNull(message)) {
            throw new RuntimeException("找不到 MQ 消息参数");
        }
        return message;
    }

    private Channel getChannel(final Object[] args) {
        Channel channel = null;
        for (Object arg : args) {
            if (arg instanceof Channel) {
                channel = (Channel) arg;
                break;
            }
        }
        if (Objects.isNull(channel)) {
            throw new RuntimeException("找不到 Channel");
        }
        return channel;
    }
}
