package com.hovel.algorithm.list;

public class RemoveListNode1 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode temp = head;
        for (int i = 2; i < 6; i++) {
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

        ListNode first = dummy;
        ListNode second = dummy;

        int step = 0;
        while (step <= n) {
            first = first.next;
            step++;
        }

        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
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
