package com.sweet.config;

import com.sweet.config.options.ZookeeperOptions;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Zookeeper 配置
 */
@Configuration
public class ZookeeperConfig {

    private final ZookeeperOptions zookeeperOptions;

    public ZookeeperConfig(ZookeeperOptions zookeeperOptions) {this.zookeeperOptions = zookeeperOptions;}

    @Bean
    public CuratorFramework curatorFramework() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(zookeeperOptions.getTimeout(), zookeeperOptions.getRetry());
        CuratorFramework client = CuratorFrameworkFactory.newClient(zookeeperOptions.getUrl(), retryPolicy);
        client.start();
        return client;
    }
}
