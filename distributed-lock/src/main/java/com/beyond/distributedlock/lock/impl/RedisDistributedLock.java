package com.beyond.distributedlock.lock.impl;

import com.beyond.distributedlock.lock.DistributedLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.Optional;

public class RedisDistributedLock implements DistributedLock {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisDistributedLock.class);


    private static final String LOCK_PREFIX = "distributed-lock::";

    private final StringRedisTemplate redisTemplate;

    private static final String LUA_LOCK_SCRIPT = "if redis.call('setnx',KEYS[1],ARGV[1]) == 1 then return redis.call('expire',KEYS[1],ARGV[2])  else return 0 end";
    private static final String LUA_UNLOCK_SCRIPT = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";

    public RedisDistributedLock(final StringRedisTemplate redisTemplate) {this.redisTemplate = redisTemplate;}

    @Override
    public boolean lock(final String key, final long timeout) {
        String realKey = String.format("%s%s", LOCK_PREFIX, key);
        long threadId = Thread.currentThread().getId();
        DefaultRedisScript<Long> lockScript = new DefaultRedisScript<>(LUA_LOCK_SCRIPT, Long.class);
        LOGGER.debug("try lock with key[{}], timeout[{}]", realKey, timeout);
        Long result = redisTemplate.execute(lockScript, Collections.singletonList(realKey), String.valueOf(threadId), String.valueOf(timeout));
        boolean success = Optional.ofNullable(result).orElse(-1L) == 1;
        LOGGER.debug("acquire redis lock {}", success ? "success" : "fail");
        return success;
    }

    @Override
    public boolean releaseLock(final String key) {
        String realKey = String.format("%s%s", LOCK_PREFIX, key);
        long threadId = Thread.currentThread().getId();

        DefaultRedisScript<Long> longDefaultRedisScript = new DefaultRedisScript<>(LUA_UNLOCK_SCRIPT, Long.class);
        LOGGER.debug("try release lock, key[{}]", realKey);
        Long result = redisTemplate.execute(longDefaultRedisScript, Collections.singletonList(realKey), String.valueOf(threadId));
        boolean success = Optional.ofNullable(result).orElse(-1L) == 1;
        LOGGER.debug("release lock[{}] {}", realKey, success ? "success" : "fail");
        return success;
    }

}
