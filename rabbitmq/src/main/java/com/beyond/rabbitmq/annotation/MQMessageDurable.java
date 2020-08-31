package com.beyond.rabbitmq.annotation;

import java.lang.annotation.*;

/**
 * @author lucifer
 * @date 2020/8/31 17:39
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MQMessageDurable {
}
