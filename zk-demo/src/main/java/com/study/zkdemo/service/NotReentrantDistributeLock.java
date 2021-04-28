package com.study.zkdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author zhou11
 * @version 1.0.0
 * @description TODO
 * @date 2021/4/28
 */
@Slf4j
@Component
public class NotReentrantDistributeLock implements DistributeLock{

    private String lockBasePath;

    private String lockName;

    private ZooKeeper zooKeeper;

    private NotReentrantDistributeLock(String lockBasePath, String lockName) {
        this.lockBasePath = lockBasePath;
        this.lockName = lockName;
        init();
    }

    private void init() {
        try {
            if (zooKeeper.exists(lockBasePath, null) == null) {
                zooKeeper.create(lockBasePath, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (KeeperException | InterruptedException e) {
            log.error("ssss");
        }
    }


    @Override
    public void lock() throws InterruptedException {
        String lockFullPath = lockBasePath + "/" + lockName;
        try {
            zooKeeper.create(lockFullPath, lockName.getBytes(StandardCharsets.UTF_8),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            log.info("创建临时节点成功，拿到锁");
        } catch (KeeperException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void unlock() {

    }
}
