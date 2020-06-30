package com.hovel.algorithm.list;

/**
 * [1,2,3,4,5] 2   ->[1,2,3,5]
 * [1] 1 -> []
 * [1,2] 2 -> [2]
 *
 */
public class RemoveListNode {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode temp = head;
        for (int i = 2; i < 3; i++) {
            ListNode node = new ListNode(i);
            temp.next = node;
            temp = node;
        }
        temp = head;

        while (temp != null) {

            System.out.print(temp.val);
            System.out.print(", ");
            temp = temp.next;
        }

        System.out.println();

        ListNode newHeadNode = removeNthFromEnd(head, 2);

        temp = newHeadNode;
        while (temp != null) {
            System.out.print(temp.val);
            System.out.print(", ");

            temp = temp.next;
        }


    }


    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode node = dummy;
        int size = 0;
        while (node != null) {
            node = node.next;
            size++;
        }

        node = dummy;
        size -= n;

        while (size > 1) {
            size--;
            node = node.next;
        }
        node.next = node.next.next;

        return dummy.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
