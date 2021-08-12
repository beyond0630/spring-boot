package com.beyond.rocketmq.producer;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Beyond
 */
@RestController
@RequestMapping("/test/producer")
public class TestProducerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestProducerController.class);

    private final RocketMQTemplate template;

    public TestProducerController(final RocketMQTemplate template) {
        this.template = template;
    }

    @GetMapping("send")
    public String send() {
        LOGGER.info("sync send message");
        template.syncSend("sync-test", "sync-test");
        return "success";
    }

    @GetMapping("send/async")
    public String asyncSend() {
        LOGGER.info("async send message");
        template.asyncSend("sync-test", "sync-test", new SendCallback() {
            @Override
            public void onSuccess(final SendResult sendResult) {
                LOGGER.info("async send message success");
            }

            @Override
            public void onException(final Throwable throwable) {
                LOGGER.info("async send message failed, {}", throwable.getMessage());
            }
        });
        return "success";
    }

}
