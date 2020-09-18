package com.beyond.distributedlock.config.options;

public interface ZookeeperOptions {

    String getUrl();

    int getTimeout();

    int getRetry();

}
