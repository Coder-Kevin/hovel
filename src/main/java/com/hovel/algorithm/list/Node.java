package com.hovel.algorithm.list;

import lombok.AllArgsConstructor;

/**
 * @author Kevin
 */
public class Node<T> {
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
