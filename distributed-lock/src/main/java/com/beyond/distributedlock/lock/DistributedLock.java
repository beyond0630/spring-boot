package com.beyond.distributedlock.lock;

/**
 * @author lucifer
 * @date 2020/9/18 17:21
 */
public interface DistributedLock {

    boolean lock(String key, long timeout);

    void releaseLock(String key);

}
