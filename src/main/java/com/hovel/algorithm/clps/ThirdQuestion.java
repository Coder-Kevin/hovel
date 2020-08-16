package com.hovel.algorithm.clps;

import org.apache.poi.ss.formula.functions.T;

import java.util.*;

/**
 * @author Kevin
 * <p>
 * 3.	根据输入生成一棵树，并打印该树。输入为 N 行数字对序列（例如：N = 2，数字对序列为（1，2），（2，3）），
 * 其中数字代表该树的节点，左边数字代表的节点是右边数字代表的节点的父节点，请根据输入的数字对序列生成一棵树，
 * 并按照广度优先的顺序打印该树。注意，存在输入无法生成树的情况，比如（1，2）（1，3）（2，4）（3，4），
 * 根据该序列生成的 Graph 中，2 节点和 3 节点同时为 4 节点的父节点，所以根据定义该 Graph不是树。如遇无法生成树的情况，
 * 请输出字符串：“Not a tree”  （引号不包括，大小写敏感）。广度优先遍历树并打印输出时，同一层级的节点根据输入时节点出现顺序打印输出
 */
public class ThirdQuestion {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Queue<TreeNode> treeNodes = new LinkedList<>();
        Map<String, TreeNode> map = new HashMap<>();

        TreeNode root = null;
        while (n > 0) {
            n--;
            String nodeString = sc.next();
            String[] nodes = nodeString.split(",");

            TreeNode fatherNode = getOrAdd(nodes[0], map);
            if (root == null) {
                root = fatherNode;
            } else {
                if (root.val > fatherNode.val) {
                    root = fatherNode;
                }
            }

            TreeNode sonNode = getOrAdd(nodes[1], map);

            if (fatherNode.getLeftNode() == null) {
                fatherNode.setLeftNode(sonNode);
            } else if (fatherNode.getRightNode() == null) {
                fatherNode.setRightNode(sonNode);
            }
        }

        if (root != null) {
            add(root, treeNodes);
        }

        for (TreeNode treeNode : treeNodes) {
            System.out.print(treeNode.val + " ");
        }

    }

    public static TreeNode getOrAdd(String val, Map<String, TreeNode> map) {
        if (map.containsKey(val)) {
            return map.get(val);
        }

        TreeNode node = new TreeNode(Integer.parseInt(val));
        map.put(val, node);
        return node;
    }

    public static void add(TreeNode node, Queue<TreeNode> list) {
        list.add(node);

        if (node.leftNode != null) {
            add(node.leftNode, list);
        }

        if (node.rightNode != null) {
            add(node.rightNode, list);
        }
    }

    public static int[] createTree() {

        return null;
    }

    static class TreeNode {
        private int val;

        private TreeNode leftNode;

        private TreeNode rightNode;

        TreeNode(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public TreeNode getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(TreeNode leftNode) {
            this.leftNode = leftNode;
        }

        public TreeNode getRightNode() {
            return rightNode;
        }

        public void setRightNode(TreeNode rightNode) {
            this.rightNode = rightNode;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", leftNode=" + leftNode +
                    ", rightNode=" + rightNode +
                    '}';
        }
    }

}
