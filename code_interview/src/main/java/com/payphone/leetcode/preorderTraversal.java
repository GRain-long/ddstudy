package com.payphone.leetcode;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class preorderTraversal {
    List<Integer> list = new ArrayList<>();

    /**
     * 递归解法
     *
     * @param root
     */
    public void preOrder(TreeNode root) {
        if (root != null) {
            list.add(root.val);
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    /**
     * 非递归解法
     *
     * @param root
     */
    public void preOrder2(TreeNode root) {
        if (root == null) return;
        // 双端队列
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                list.add(root.val);
            } else {
                TreeNode pop = stack.pop();
                root = pop.right;
            }
        }
    }
}
