package com.study.zkdemo.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @author zhou11
 * @version 1.0.0
 * @description TODO
 * @date 2021/4/27
 */
@Slf4j
public class CustWatcher implements Watcher {
    @Override
    public void process(WatchedEvent watchedEvent) {
        log.info("监听事件的状态: {}",watchedEvent.getState());
        log.info("监听事件的路径: {}",watchedEvent.getPath());
        log.info("监听事件的类型: {}",watchedEvent.getType());
    }
}
