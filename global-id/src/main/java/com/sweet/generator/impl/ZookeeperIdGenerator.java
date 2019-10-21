package com.sweet.generator.impl;


import com.sweet.generator.IdGenerator;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.stereotype.Service;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2019/10/21
 */
@Service
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
    public long generate() {
        String pathPrefix = String.format("%s/%s", PATH, NODE_NAME);
        String path = zkClient.createEphemeralSequential(pathPrefix, null);
        return Long.valueOf(path.replace(pathPrefix, ""));
    }

}
