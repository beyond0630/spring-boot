package com.beyond.globalid.generator.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

import com.beyond.globalid.generator.IdGenerator;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

/**
 * @author lucifer
 * @date 2020/9/27 10:54
 */
public class RedisIdGenerator implements IdGenerator {

    private static final String KEY_PREFIX = "id-";

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisIdGenerator(final RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String generate() {
        LocalDateTime now = LocalDateTime.now();
        String timePrefix = getTimePrefix(now);
        String key = String.format("%s%s", KEY_PREFIX, timePrefix);
        long num = generate(key, getExpireAtTime(now));
        return timePrefix + String.format("%1$06d", num);
    }

    /**
     * 获取有过期时间的自增长ID
     */
    public long generate(String key, Date expireTime) {
        RedisAtomicLong counter = new RedisAtomicLong(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
        Long expire = counter.getExpire();
        if (Objects.isNull(expire) || expire == -1) {
            counter.expireAt(expireTime);
        }
        return counter.incrementAndGet();
    }

    private String getTimePrefix(LocalDateTime now) {
        return now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    private Date getExpireAtTime(LocalDateTime now) {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = now.plusSeconds(2);
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }
}
