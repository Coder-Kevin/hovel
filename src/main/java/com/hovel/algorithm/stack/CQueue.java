package com.hovel.algorithm.stack;

import java.util.Stack;

/**
 * 用栈实现队列
 * 主要考察：栈   -- 先进后出
 * 队列 -- 先进先出
 *
 * 两个栈完成先进先出
 */
public class CQueue {

    public static void main(String[] args) {
        CQueue cQueue = new CQueue();
        cQueue.appendTail(5);
        cQueue.appendTail(2);

        System.out.println(cQueue.stack1.pop());
        System.out.println(cQueue.stack1.pop());

        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());

    }

    private Stack stack1;

    private Stack stack2;

    public CQueue() {
        this.stack1 = new Stack();
        this.stack2 = new Stack();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        if (stack2.empty()){
            if (stack1.empty()) {
                return -1;
            }
            while(!stack1.empty()){
                stack2.push(stack1.pop());
            }
            return (int) stack2.pop();
        }

        return (int) stack2.pop();
    }
}
