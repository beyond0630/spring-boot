package com.beyond.distributedlock.config;

import com.beyond.distributedlock.lock.DistributedLock;
import com.beyond.distributedlock.lock.impl.ZookeeperDistributedLock;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lucifer
 * @date 2020/9/18 17:50
 */
@Configuration
public class BeanConfig {

    @Bean
    public DistributedLock distributedLock(final CuratorFramework zkClient) {
        return new ZookeeperDistributedLock(zkClient);
    }
}
