package com.beyond.distributedlock.config;

import com.beyond.distributedlock.lock.DistributedLock;
import com.beyond.distributedlock.lock.impl.RedisDistributedLock;
import com.beyond.distributedlock.utils.StandardObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * @author lucifer
 * @date 2020/9/18 17:50
 */
@Configuration
public class BeanConfig {

//    @Bean
//    public DistributedLock distributedLock(final CuratorFramework zkClient) {
//        return new ZookeeperDistributedLock(zkClient);
//    }

    @Bean
    public DistributedLock distributedLock(final StringRedisTemplate redisTemplate) {
        return new RedisDistributedLock(redisTemplate);
    }

    @Bean
    public Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer() {
        final Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer =
                new Jackson2JsonRedisSerializer<>(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(StandardObjectMapper.getInstance());
        return jackson2JsonRedisSerializer;
    }
}
