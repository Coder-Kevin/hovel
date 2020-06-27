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

        josephTest.createCircleList();
        Node node = josephTest.first;
        Node next = null;
        Node before = null;

        int count = 0;
        // todo 需要纠错
        while (node != node.getNext()) {
            if (count == 3) {
                node = before;
                node.setNext(next.getNext());
                count = 0;
            } else {
                before = node;
                next = node.getNext();
                node = next;
                count ++;
            }

        }

        System.out.println(node.getItem());
        System.out.println(next.getItem());
        System.out.println(before.getItem());
    }

    @Test
    public void testCircleList() {
        JosephTest josephTest = new JosephTest();
        josephTest.createCircleList();
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

    public void createCircleList() {
        for (int i = 1; i <= 41; i++) {
            // 首节点
            if (i == 1) {
                first = new Node(i, null);
                pre = first;
                continue;
            }
            // 尾节点的下一个节点指向首节点即可构成循环链表
            if (i == 41) {
                Node tail = new Node(41, first);
                pre.setNext(tail);
                continue;
            }

            Node newNode = new Node(i, null);
            pre.setNext(newNode);
            pre = newNode;
        }
    }

}
