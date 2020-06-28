package com.hovel.algorithm.list;

import org.junit.Test;

/**
 * @author Kevin
 * 约瑟夫问题
 */
public class JosephTest {

    /**
     * 记录首节点
     */
    private Node first;

    /**
     * 记录前一个节点
     */
    private Node pre;

    public static void main(String[] args) {
        JosephTest josephTest = new JosephTest();
        josephTest.joseph(josephTest);
    }

    public void joseph(JosephTest josephTest) {

        josephTest.createCircleList(41);
        Node node = josephTest.first;// 当前节点，初始为首节点
        Node next = null;// 当前节点的下一节点
        Node before = null;// 当前节点的上一节点

        int count = 1;// 初始为1，因为上面已经node赋值为first了
        /**
         * 当全部kill掉所有报数=3的人后，仅剩2个元素的时候走进count==3中
         * node.next = next.next = node 如此结束循环
         */
        while (node != node.getNext()) {
            if (count == 3) {

                /**
                 * node节点置为上一节点，并将上一节点的下节点置为next（也就是死掉节点）的下节点
                 */
                node = before;
                node.setNext(next.getNext());
                count = 0;
            } else {
                /**
                 * 1.before暂存当前节点
                 * 2.next存储当前节点的下一节点
                 * 3.当前节点置为next成为下一循环的当前节点
                 * 4.count+1
                 */
                before = node;
                next = node.getNext();
                node = next;
                count++;

            }

        }

        System.out.println(node.getItem());
        System.out.println(node.getNext().getItem());
        System.out.println(next.getItem());
    }

    @Test
    public void testCircleList() {
        JosephTest josephTest = new JosephTest();
        josephTest.createCircleList(8);
        Node node = josephTest.first;
        Node next = null;
        for (int i = 0; i < 43; i++) {
            next = node.getNext();
            node = next;
            if (next != null) {
                System.out.println(next.getItem());
            }

        }
    }

    public void createCircleList(int n) {
        for (int i = 1; i <= n; i++) {
            // 首节点
            if (i == 1) {
                first = new Node(i, null);
                pre = first;
                continue;
            }
            // 尾节点的下一个节点指向首节点即可构成循环链表
            if (i == n) {
                Node tail = new Node(n, first);
                pre.setNext(tail);
                continue;
            }

            Node newNode = new Node(i, null);
            pre.setNext(newNode);
            pre = newNode;
        }
    }

}
