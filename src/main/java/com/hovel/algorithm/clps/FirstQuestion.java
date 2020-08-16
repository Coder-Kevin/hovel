package com.hovel.algorithm.clps;

import java.util.Scanner;

/**
 * 1.	有 n 个同学围成一圈，其 id 依次为 1，2，3...n（n 号挨着 1 号）。现在从 1 号开始报数，第一回合报到m 的人就出局，
 * 第二回合从出局的下一个开始报数，报到 m^2 的同学出局。
 * <p>
 * 以此类推直到最后一个回合报到 m^(n-1)的人出局，直到剩下最后一个同学。输出这个同学的编号。n<=15,m<=5
 *
 * @author Kevin
 */
public class FirstQuestion {

    /**
     * 记录首节点
     */
    private static Node first;

    /**
     * 记录前一个节点
     */
    private static Node pre;

    private static final int MAX_N = 15;

    private static final int MAX_M = 5;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter n:");
        int n = sc.nextInt();

        if (n > MAX_N) {
            throw new RuntimeException("n rather than 15!");
        }
        System.out.println("Please Enter m:");
        int m = sc.nextInt();
        if (m > MAX_M) {
            throw new RuntimeException("m rather than 5!");
        }

        long start = System.currentTimeMillis();
        createCircleList(n);

        System.out.println("Result：" + deal(n, m));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static int deal(int n, int m) {
        // 当前节点，初始为首节点
        Node node = first;

        // 当前节点的上一节点
        Node before = null;

        int number = 0;
        int a = 1;

        while (a < n) {
            number++;
            if (number == Math.pow(m, a)) {
                before.setNext(node.getNext());
                node = node.getNext();
                a++;
                number = 0;
                continue;
            }
            before = node;
            node = node.getNext();
        }
        return (int) before.getItem();
    }

    public static void createCircleList(int n) {
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

    /**
     * @author Kevin
     */
    static class Node<T> {
        /**
         * 下一个节点
         */
        private Node next;

        /**
         * 数据
         */
        private T item;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public T getItem() {
            return item;
        }

        public void setItem(T item) {
            this.item = item;
        }

    }


}
