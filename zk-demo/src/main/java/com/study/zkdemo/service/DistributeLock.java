package com.study.zkdemo.service;

/**
 * @author zhou11
 * @version 1.0.0
 * @description TODO
 * @date 2021/4/28
 */
public interface DistributeLock {

    /**
     * @return void
     * @description 获取锁
     * @author zhou11
     * @date 2021/4/28
     */
    void lock() throws InterruptedException;

    /**
     * @param
     * @description TODO
     * @return void
     * @author zhou11
     * @date 2021/4/28
     */
    void unlock();
}
