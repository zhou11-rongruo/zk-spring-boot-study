package com.study.zkdemo;

import com.study.zkdemo.util.ZkUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ZkDemoApplicationTests {

    @Autowired
    private ZkUtil zkUtil;

    @Test
    void contextLoads() {
    }

    /**
     * 新增节点
     */
    @Test
    public void testCreateNode() {
        zkUtil.createPerNode("/demo", "auskat");
    }

}
