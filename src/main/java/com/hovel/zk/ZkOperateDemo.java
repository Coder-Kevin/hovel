package com.hovel.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.data.Stat;

@Slf4j
public class ZkOperateDemo {

    private static final String ZK_ADDRESS = "127.0.0.1:2181";

    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                ZK_ADDRESS,
                new RetryNTimes(10, 5000)
        );
        client.start();
        System.out.println("zk client start successfully!");

        Stat stat = client.checkExists().forPath("/locks");
        log.info("/locks state is {}", stat);


        if (stat == null) {
            client.create().forPath("/locks/lock_01");
        }

        client.close();

    }

}
