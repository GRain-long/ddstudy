package com.payphone.leetcode.old;

import java.util.*;

public class BinaryTree {
    /**
     * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。 例如: 给定二叉树: [3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9 20
     * / \
     * 15 7
     * 返回其层次遍历结果： [ [3], [9,20], [15,7] ]
     * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
     * 另解 用DFS 深度优先遍历。深度优先遍历是按截面来进行的每次加入一个纵坐标的数
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> all = new ArrayList<List<Integer>>();
        if (root == null) return all;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);//入队
        while (!queue.isEmpty()) {
            int size = queue.size();//获取当前层数的节点数
            List<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < size; i++) {//每次都把当前层扫描完
                TreeNode cur = queue.poll();
                list.add(cur.val);
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            all.add(list);
        }
        return all;
    }

    /**
     * 二叉树的下一个结点
     * 题目描述
     * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
     * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
     * 我是看不懂他到底想求什么结点的下一个结点。
     * 也没有提输入输出。
     * 最后还是看懂了。方法中给出的参数A，我们要求的就是A的下一个结点
     * <p>
     * 解法一
     * 先获得根结点。再求中序遍历。假如我们要求A的下一个结点
     * 设置temp = null 标识有没有发现A
     * 在中序遍历时，发现结点A，我们用temp=A
     * 在出栈时发现temp!=null,则当前出栈的结点就是我们要的结点。
     * <p>
     * 解法二 看leetcode 官方题解
     */

    //采用解法一进行解题
    public TreeLinkNode GetNext1(TreeLinkNode pNode) {
        TreeLinkNode temp = null;
        int val = pNode.val;// 我们要找到中序遍历val的下一个点
        // 找到根结点 好进行中序遍历
        while (pNode.next != null) {
            pNode = pNode.next;
        }
        TreeLinkNode root = pNode;
        Stack<TreeLinkNode> stack = new Stack<TreeLinkNode>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                root = stack.pop();
                if (temp != null) {
                    return root;
                }
                if (root.val == val)
                    temp = root;
                root = root.right;
            }
        }
        return null;
    }

    //采用解法二进行解题
    TreeLinkNode GetNext2(TreeLinkNode node) {
        if (node == null)
            return null;
        if (node.right != null) { // 如果有右子树，则找右子树的最左节点
            node = node.right;
            while (node.left != null)
                node = node.left;
            return node;
        }
        while (node.next != null) { // 没右子树，则找第一个当前节点是父节点左孩子的节点
            if (node.next.left == node)
                return node.next;
            node = node.next;
        }
        return null; // 退到了根节点仍没找到，则返回null
    }

    /**
     * 从中序与后序遍历序列构造二叉树
     * 这个创建时要先建立右子树 在建立左子树。画图就知道了
     * 这是一道非常经典的题目。考察你对递归和二叉树的理解程度。
     * 剑指offer 题解写的一般，看本科教材的递归代码。
     * 非递归看leetcode官方题解
     * 主要是  我也不知道怎么写了  哈哈哈哈
     * */


    /**
     * 测试代码
     * @param args
     */
    public static void main(String[] args) {
        Tree tree = new Tree(-1);
        tree.left = new Tree(1);
        tree.right = new Tree(2);
        tree.left.left = new Tree(3);
        tree.left.right = new Tree(4);
        tree.right.left = new Tree(5);
        tree.right.right = new Tree(6);
        BinaryTree test = new BinaryTree();
        int level = test.level(tree);
        System.out.println(level);
    }

    public int level(Tree tree) {
        int max = 0;
        Queue<Tree> queue = new LinkedList<Tree>();
        queue.add(tree);
        while(!queue.isEmpty()) {
            int size = queue.size();
            if(max<size)max = size;
            for(int i=0;i<size;i++) {
                Tree temp = queue.poll();
                if(temp.left!=null) queue.add(temp.left);
                if(temp.right!=null) queue.add(temp.right);
            }
        }
        return max;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}

class Tree {
    Tree left;
    Tree right;
    int val;

    public Tree(int val) {
        this.val = val;
    }

    public Tree() {
    }
}

