package com.beyond.distributedlock.lock.impl;

import java.util.concurrent.TimeUnit;

import com.beyond.distributedlock.lock.DistributedLock;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lucifer
 * @date 2020/9/18 17:23
 */
public class ZookeeperDistributedLock implements DistributedLock {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperDistributedLock.class);

    private static final String LOCK_PREFIX = "/distributed-lock/";

    private final CuratorFramework zkClient;

    private ThreadLocal<InterProcessMutex> mutexThreadLocal;

    public ZookeeperDistributedLock(final CuratorFramework zkClient) {this.zkClient = zkClient;}

    @Override
    public boolean lock(final String key, final long timeout) {
        InterProcessMutex mutex = new InterProcessMutex(zkClient, String.format("%s%s", LOCK_PREFIX, key));
        mutexThreadLocal = ThreadLocal.withInitial(() -> mutex);
        try {
            LOGGER.debug("try lock with key[{}], timeout[{}]", key, timeout);
            boolean acquire = mutex.acquire(timeout, TimeUnit.MILLISECONDS);
            if (acquire) {
                LOGGER.debug("acquire zookeeper lock success");
            } else {
                LOGGER.debug("acquire zookeeper lock fail");
            }
            return acquire;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return false;
    }

    @Override
    public void releaseLock(final String key) {
        InterProcessMutex mutex = mutexThreadLocal.get();
        if (mutex.isAcquiredInThisProcess()) {
            LOGGER.debug("release zookeeper lock");
            try {
                mutex.release();
            } catch (Exception e) {
                LOGGER.debug("release zookeeper lock fail");
            }
        }
        mutexThreadLocal.remove();
    }
}
