package com.beyond.globalid.config;

import com.beyond.globalid.config.props.options.ZookeeperOptions;
import com.beyond.globalid.generator.IdGenerator;
import com.beyond.globalid.generator.impl.RedisIdGenerator;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2019/10/21
 */
@Configuration
public class BeanConfig {

    private final ZookeeperOptions zookeeperOptions;

    public BeanConfig(ZookeeperOptions zookeeperOptions) {this.zookeeperOptions = zookeeperOptions;}

    @Bean
    public ZkClient zkClient() {
        return new ZkClient(zookeeperOptions.getUrl(), zookeeperOptions.getTimeout());
    }

    @Bean
    public IdGenerator idGenerator(RedisTemplate<String, Object> redisTemplate) {
        return new RedisIdGenerator(redisTemplate);
    }

}
