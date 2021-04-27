package com.study.zkdemo.util;

import com.study.zkdemo.config.ZookeeperConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhou11
 * @version 1.0.0
 * @description TODO
 * @date 2021/4/27
 */
@Slf4j
@Component
public class ZkUtil {

    @Autowired
    private ZooKeeper zkClient;

    public boolean createPerNode(String path, String data) {
        try {
            zkClient.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            return true;
        } catch (Exception e) {
            log.error("创建持久化节点异常，路径: {}, 数据: {}, 异常: {}", path, data, e);
            return false;
        }
    }

    public boolean createTmpNode(String path, String data) {
        try {
            // 参数1：要创建的节点的路径； 参数2：节点数据 ； 参数3：节点权限 ；参数4：节点的类型
            zkClient.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            return true;
        } catch (Exception e) {
            log.error("创建临时节点异常，路径: {}, 数据: {}, 异常: {}", path, data, e);
            return false;
        }
    }

    public boolean createNode(String path, String data, List<ACL> acl, CreateMode createMode) {
        try {
            // 参数1：要创建的节点的路径； 参数2：节点数据 ； 参数3：节点权限 ；参数4：节点的类型
            zkClient.create(path, data.getBytes(), acl, createMode);
            return true;
        } catch (Exception e) {
            log.error("创建节点异常，路径: {}, 数据: {}, 异常: {}", path, data, e);
            return false;
        }
    }

    public boolean updateNode(String path, String data) {
        try {
            // zk的数据版本是从0开始计数的。如果客户端传入的是-1，则表示zk服务器需要基于最新的数据进行更新。如果对zk的数据节点的更新操作没有原子性要求则可以使用-1.
            // version参数指定要更新的数据的版本, 如果version和真实的版本不同, 更新操作将失败. 指定version为-1则忽略版本检查
            zkClient.setData(path, data.getBytes(), -1);
            return true;
        } catch (Exception e) {
            log.error("修改节点异常，路径: {}, 数据: {}, 异常: {}", path, data, e);
            return false;
        }
    }

    public boolean deleteNode(String path) {
        try {
            // version参数指定要更新的数据的版本, 如果version和真实的版本不同, 更新操作将失败. 指定version为-1则忽略版本检查
            zkClient.delete(path, -1);
            return true;
        } catch (Exception e) {
            log.error("删除节点异常，路径: {}, 异常: {}", path, e);
            return false;
        }
    }

    public Stat exists(String path, boolean needWatch) {
        try {
            return zkClient.exists(path, needWatch);
        } catch (Exception e) {
            log.error("判断指定节点是否存在异常，路径: {}, 异常: {}", path, e);
            return null;
        }
    }

    public Stat exists(String path, Watcher watcher) {
        try {
            return zkClient.exists(path, watcher);
        } catch (Exception e) {
            log.error("判断指定节点是否存在异常，路径: {}, 异常: {}", path, e);
            return null;
        }
    }

    public List<String> getChildren(String path) throws KeeperException, InterruptedException {
        List<String> list = zkClient.getChildren(path, false);
        return list;
    }

    public String getData(String path, Watcher watcher) {
        try {
            Stat stat = new Stat();
            byte[] bytes = zkClient.getData(path, watcher, stat);
            return new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
