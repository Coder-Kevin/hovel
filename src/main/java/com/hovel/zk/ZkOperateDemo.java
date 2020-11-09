package com.hovel.zk;

import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.transaction.CuratorOp;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

@Slf4j
public class ZkOperateDemo {

    private static final String ZK_ADDRESS = "127.0.0.1:2188";

    private static CuratorFramework client = null;

    @Before
    public void connect() {
        client = CuratorFrameworkFactory.newClient(
                ZK_ADDRESS,
                new RetryNTimes(10, 5000)
        );

        client.start();
        log.info("zk client start successfully!");
    }

    @After
    public void closeConnect() {
        log.info("zk client close successfully!");
        client.close();
    }

    @Test
    public void base() throws Exception {
        client = CuratorFrameworkFactory.newClient(
                ZK_ADDRESS,
                new RetryNTimes(10, 5000)
        );

        client.start();
        log.info("zk client start successfully!");

        Stat stat = client.checkExists().forPath("/locks");
        log.info("/locks state is {}", stat);

        String path = "/test";
        if (null == client.checkExists().forPath(path)) {
            // 持久化节点
            client.create().forPath(path, "Hello".getBytes());
        }

        Stat tmpStat = client.checkExists().forPath("/test/tmp");
        log.info("临时节点/tmp ：{}", tmpStat);

        // 临时节点
        client.create().withMode(CreateMode.EPHEMERAL).forPath("/test/tmp");

        // 顺序节点
        client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/test/001");
        client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/test/002");
        client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/test/003");

        log.info("临时节点/tmp ：{}", client.checkExists().forPath("/test/tmp"));

        // 获取所有子节点
        List<String> strings = client.getChildren().forPath("/");
        log.info("/ has children:{}", strings);

    }

    @Test
    public void data() throws Exception {
        client.create().withMode(CreateMode.EPHEMERAL).forPath("/data", "test set data".getBytes());
        String data = new String(client.getData().forPath("/data"));
        log.info("Path /test has data: {}", data);

        client.setData().forPath("/data", "new data".getBytes());

        data = new String(client.getData().forPath("/data"));
        log.info("After reset Path /test has data: {}", data);
    }

    @Test
    public void delete() throws Exception {
        if (client.checkExists().forPath("/test") == null) {
            client.create().forPath("/test");
            client.create().forPath("/test/hello");
            client.create().forPath("/test/hello2");
        }

        // 删除 包含子节点时无法删除
        client.delete().forPath("/test/hello");

        // 级联删除
        client.delete().guaranteed().deletingChildrenIfNeeded().forPath("/test");

    }

    @SneakyThrows
    @Test
    public void transaction() {
        if (client.checkExists().forPath("/trans") != null){
            log.info("/trans已经存在");
            client.delete().forPath("/trans");
        }
        CuratorOp createOp = client.transactionOp().create().forPath("/trans");
        CuratorOp setDataOp = client.transactionOp().setData().forPath("/trans", "data".getBytes());
        CuratorOp reCreateOp = client.transactionOp().create().forPath("/trans");

        List<CuratorTransactionResult> curatorTransactionResults = client.transaction().forOperations(createOp, setDataOp, reCreateOp);
        curatorTransactionResults.forEach(curatorTransactionResult -> log.info("执行结果：{}", JSONObject.toJSONString(curatorTransactionResult)));
    }

}
