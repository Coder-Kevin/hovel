package com.hovel.algorithm.list;

public class MergeListNode {


    public static void main(String[] args) {
//        ListNode l1 = new ListNode(2,
//                new ListNode(2,
//                        new ListNode(4, new ListNode(6, null))));
//        ListNode l2 = new ListNode(1,
//                new ListNode(3,
//                        new ListNode(4, new ListNode(5,
//                                new ListNode(7, null)))));


        ListNode l1 = null;
        ListNode l2 = new ListNode(0,null);


        ListNode temp = mergeTwoLists(l1, l2);
        while (temp != null) {
            System.out.print(temp.val);
            System.out.print(", ");

            temp = temp.next;
        }
    }


    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode temp = l2;

        ListNode pre = null;
        ListNode node = l1;

        while (temp != null) {
            while (node != null) {
                // l1中当前节点小于来比较的l2中的节点
                if (node.val < temp.val) {
                    pre = node;
                    node = node.next;
                    // 假设l2最后一个元素多与l1时 直接放到尾部
                    if (node == null) {
                        pre.next = new ListNode(temp.val, null);
                        break;
                    }

                    // 当且当前node 小于 l2来比较节点，且 node的下一个节点大于它，就把它插入到中间来
                    if (node.val > temp.val) {
                        pre.next = new ListNode(temp.val, node);
                        break;
                    } else { // 否则就继续循环
                        continue;
                    }

                } else if (node.val == temp.val) { // l1中当前节点等于来比较的l2中的节点
                    pre = node;
                    pre.next = new ListNode(temp.val, node.next);
                    node = node.next;
                    break;
                } else { // l1中当前节点大于来比较的l2中的节点
                    pre = node;
                    if (node == l1) {
                        l1 = new ListNode(temp.val, pre);
                    } else {
                        ListNode newListNode = new ListNode(temp.val, pre.next);
                        pre.next = newListNode;
                    }

                    node = node.next;
                    break;
                }
            }

            node = l1;
            temp = temp.next;
        }

        return l1;
    }


}
