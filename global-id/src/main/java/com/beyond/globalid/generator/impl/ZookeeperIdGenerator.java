package com.beyond.globalid.generator.impl;


import com.beyond.globalid.generator.IdGenerator;
import org.I0Itec.zkclient.ZkClient;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2019/10/21
 */
public class ZookeeperIdGenerator implements IdGenerator {

    private static final String PATH = "/global-id";

    private static final String NODE_NAME = "id-";

    private final ZkClient zkClient;

    public ZookeeperIdGenerator(ZkClient zkClient) {
        this.zkClient = zkClient;
        if (!zkClient.exists(PATH)) {
            zkClient.createPersistent(PATH);
        }
    }

    @Override
    public String generate() {
        String pathPrefix = String.format("%s/%s", PATH, NODE_NAME);
        String path = zkClient.createEphemeralSequential(pathPrefix, null);
        return path.replace(pathPrefix, "");
    }

}
