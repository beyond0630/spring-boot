package com.sweet.zookeeper;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZookeeperWatcher implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    private static Stat stat = new Stat();

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {  //zk连接成功通知事件
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                connectedSemaphore.countDown();
            } else if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {  //zk目录节点数据变化通知事件
                try {
                    System.out.println("配置已修改，新值为：" + new String(zk.getData(watchedEvent.getPath(), true, stat)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String path = "/username";
        zk = new ZooKeeper("127.0.0.1:2181", 5000, new ZookeeperWatcher());
        connectedSemaphore.await();
        System.out.println(new String(zk.getData(path, true, stat)));
        System.in.read();
    }
}
