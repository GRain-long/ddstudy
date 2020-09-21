package com.payphone.sword;

import java.util.ArrayList;

/**
 * 对称二叉树
 */
public class SymmetricalTree {

    static ArrayList<Integer> list = new ArrayList<>();

    boolean test() {
        int start = 0, end = list.size() - 1;
        while (start != end && start < end) {
            if (list.get(start) != list.get(end)) {
                return false;
            } else {
                start++;
                end--;
            }
        }
        return true;
    }

    // 判断元素是否对称
    public static void main(String[] args) {
    }

    // 记录中序遍历的序列
    public void inorder(TreeNode root) {
        if (root == null){
            list.add(null);
            return;
        }
        inorder(root.left);
        list.add(root.val);
        inorder(root.right);
    }

    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }

    }
}
