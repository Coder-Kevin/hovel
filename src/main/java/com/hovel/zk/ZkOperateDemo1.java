package com.hovel.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.GetChildrenBuilder;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.util.List;

@Slf4j
public class ZkOperateDemo1 {

    private static final String ZK_ADDRESS = "127.0.0.1:2181";

    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                ZK_ADDRESS,
                new RetryNTimes(10, 5000)
        );
        client.start();
        System.out.println("zk client start successfully!");

        final String path = "/test";

        Stat stat = client.checkExists().forPath(path);
        log.info("/locks state is {}", stat);

        if (stat == null) {
            // 永久节点
            client.create().withMode(CreateMode.PERSISTENT).forPath(path);
        }

        client.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath(path);
        client.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath(path);

        stat = client.checkExists().forPath(path);
        log.info("/locks state is {}", stat);

        client.close();

    }

    @Test
    public void zkList() throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                ZK_ADDRESS,
                new RetryNTimes(10, 5000)
        );
        client.start();
        System.out.println("zk client start successfully!");
        GetChildrenBuilder childrenBuilder = client.getChildren();
        List<String> paths = childrenBuilder.forPath("/");
        log.info("{}", paths);

        client.close();
    }


    /**
     * zk Watcher
     */




}
