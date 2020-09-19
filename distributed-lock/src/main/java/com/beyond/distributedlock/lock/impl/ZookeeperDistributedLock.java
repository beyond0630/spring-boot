package com.beyond.distributedlock.lock.impl;

import com.beyond.distributedlock.lock.DistributedLock;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author lucifer
 * @date 2020/9/18 17:23
 */
public class ZookeeperDistributedLock implements DistributedLock {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperDistributedLock.class);

    private static final String LOCK_PREFIX = "/distributed-lock/";

    private final CuratorFramework zkClient;

    private final ThreadLocal<InterProcessMutex> mutexThreadLocal = ThreadLocal.withInitial(() -> null);

    public ZookeeperDistributedLock(final CuratorFramework zkClient) {this.zkClient = zkClient;}

    @Override
    public boolean lock(final String key, final long timeout) {
        String realKey = String.format("%s%s", LOCK_PREFIX, key);
        InterProcessMutex mutex = new InterProcessMutex(zkClient, realKey);
        mutexThreadLocal.set(mutex);
        try {
            LOGGER.debug("try lock with key[{}], timeout[{}]", realKey, timeout);
            boolean success = mutex.acquire(timeout, TimeUnit.MILLISECONDS);
            LOGGER.debug("acquire zookeeper lock {}", success ? "success" : "fail");
            return success;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean releaseLock(final String key) {
        InterProcessMutex mutex = mutexThreadLocal.get();
        if (mutex.isAcquiredInThisProcess()) {
            try {
                LOGGER.debug("released lock key[{}]", String.format("%s%s", LOCK_PREFIX, key));
                mutex.release();
            } catch (Exception e) {
                LOGGER.debug("release zookeeper lock fail");
            }
        }
        mutexThreadLocal.remove();
        return true;
    }
}
