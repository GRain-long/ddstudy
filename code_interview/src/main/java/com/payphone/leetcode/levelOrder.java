package com.payphone.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 层序遍历
 */


//层序遍历
public class levelOrder {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode tree = queue.remove();
            System.out.println(tree.val);
            if (tree.left != null) queue.add(tree.left);
            if (tree.right != null) queue.add(tree.right);
        }
        return null;
    }
}
