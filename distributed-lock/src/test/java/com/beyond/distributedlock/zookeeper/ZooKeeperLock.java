package com.beyond.distributedlock.zookeeper;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * zookeeper工具类：
 * <p>
 * 更多免费资料，更多高清内容，更多java技术，欢迎访问网站
 * 极客慧：www.jikeh.cn
 * 如果你希望进一步深入交流，请加入我们的大家庭QQ群：375412858
 */
public class ZooKeeperLock {

    private static final Logger logger = LoggerFactory.getLogger(ZooKeeperLock.class);

    private static final CountDownLatch connectedSemaphore = new CountDownLatch(1);

    private ZooKeeper zookeeper;

    public ZooKeeperLock() {

        // 连接zookeeper server，是异步创建会话的，那我们怎么知道zk session建立成功了呢？
        // 通过一个监听器+CountDownLatch，来确认真正建立了zk server的连接
        try {
            this.zookeeper = new ZooKeeper(
                    "localhost:2181",
                    50000,
                    new ZooKeeperWatcher());

            //打印即使状态：验证其是不是异步的？
            logger.info(String.valueOf(zookeeper.getState()));

            try {
                // CountDownLatch：简而言之 初始化——非0；非0——等待；0——往下执行
                connectedSemaphore.await();
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
            }

            logger.info("ZooKeeper session established......");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 建立zk session的watcher：
     */
    private static class ZooKeeperWatcher implements Watcher {

        public void process(WatchedEvent event) {
            if (Event.KeeperState.SyncConnected == event.getState()) {
                connectedSemaphore.countDown();
            }
        }

    }

    /**
     * 静态内部类实现单例：
     */
    private static class Singleton {

        private static final ZooKeeperLock instance;

        static {
            instance = new ZooKeeperLock();
        }

        static ZooKeeperLock getInstance() {
            return instance;
        }

    }

    /**
     * 获取单例：
     *
     */
    public static ZooKeeperLock getInstance() {
        return Singleton.getInstance();
    }

    /**
     * 重试获取分布式锁：
     *
     */
    public void acquireDistributedLock(int adId) {
        String path = "/sweet-lock-" + adId;

        try {
            zookeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            logger.info("success to acquire lock for adId = " + adId);
        } catch (Exception e) {
            logger.info("====== can not acquire lock, will be retry ======");
            // 如果那个广告对应的锁node，已经存在了，就是已经被别人加锁了，那么就这里就会报错
            // NodeExistsException
            int count = 0;
            while (true) {
                try {
                    Thread.sleep(1000);
                    zookeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                } catch (Exception e2) {
                    count++;
                    logger.info("the " + count + " times try to acquire lock for adId = " + adId);
                    continue;
                }
                logger.info("success to acquire lock for adId = " + adId + " after " + count + " times try......");
                break;
            }
        }
    }

    /**
     * 释放掉分布式锁：
     */
    public void releaseDistributedLock(int adId) {
        String path = "/sweet-lock-" + adId;
        try {
            zookeeper.delete(path, -1);
            logger.info("release the lock for adId = " + adId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Arrays.stream(new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 })
                .mapToObj(x -> (Runnable) () -> testLock(x / 3 + 1))
                .forEach(executorService::execute);
        executorService.shutdown();
    }

    static void testLock(int i) {
        ZooKeeperLock zkSession = ZooKeeperLock.getInstance();
        //1、获取锁：
        zkSession.acquireDistributedLock(i);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //2、执行一些修改共享资源的操作
        logger.info("I am updating common resource！");
        //3、释放锁
        zkSession.releaseDistributedLock(i);
    }

}