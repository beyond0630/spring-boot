package com.beyond.globalid.config;

import com.beyond.globalid.props.options.ZookeeperOptions;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
