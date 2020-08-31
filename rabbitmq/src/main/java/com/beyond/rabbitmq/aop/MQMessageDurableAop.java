package com.beyond.rabbitmq.aop;

import java.util.Objects;

import com.beyond.rabbitmq.service.MQMessageService;
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

    @Pointcut("@annotation(com.beyond.rabbitmq.annotation.MQMessageDurable)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object doLock(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        Message message = getMessage(args);
        String messageId = message.getMessageProperties().getMessageId();
        boolean consumedMessage = mqMessageService.hasConsumedMessage(messageId);
        if (consumedMessage) {
            LOGGER.info("消息重复消费, messageId[{}]", messageId);
            return null;
        }
        LOGGER.debug("args: [{}]", args);
        // 执行业务
        Object result = point.proceed();
        LOGGER.debug("result: [{}]", result);
        // 保存消息到数据库
        mqMessageService.saveMessage(message);
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
}
