package com.sweet;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * spring-boot
 *
 * @author hdb001
 * @date 2019/10/17
 */
@Configuration
public class MqConstants {

    @Bean
    public Exchange direct() {
        return ExchangeBuilder.directExchange("test.direct").build();
    }

}
