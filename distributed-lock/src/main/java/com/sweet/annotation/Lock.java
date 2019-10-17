package com.sweet.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Lock {

    /**
     * 分布式锁的键
     */
    String key();

    /**
     * 锁释放时间，默认五秒
     */
    long timeout() default 5 * 1000;

    /**
     * 时间格式，默认：毫秒
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;
}
