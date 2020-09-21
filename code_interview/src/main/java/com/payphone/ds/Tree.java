package com.payphone.ds;


import java.util.Stack;

public class Tree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 前序非递归遍历
    // 左子树一直入栈，没有左子树的之后，出栈一个元素 考虑其是否有右子树。【没有左子树就一直出栈，看右子树】
    public void preOrder(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.empty() || node != null) {
            if (node != null) {
                System.out.println(node.val);
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                node = node.right;
            }
        }
    }

    // 前序遍历
    public void preOrder2(TreeNode node) {
        if (node == null) return;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.empty() || node != null) {
            while (node != null) {
                stack.push(node);
                // 操作数据
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
    }

    // 中序遍历非递归 与前序遍历类似，只是数据的操作放在了出栈那块
    public void inOrder(TreeNode node) {
        if (node == null) return;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.empty() || node == null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            // 中序遍历 操作数据位置
            node = node.right;
        }
    }

    /**
     * 后序遍历 左 右 根
     * 第二次 访问根的时候 根节点才能出栈
     * 不能直接出栈，需要两次标记才能出栈。
     *
     * @param node
     */
    public void afterOrder(TreeNode node) {
        if (node == null) return;
        TreeNode visit = null;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.empty() || node == null) {
            // 左子树一直入栈
            if(node != null){
                stack.push(node);
                visit = node;
                node = node.left;
            }else{
                /**
                 * 看出栈几次。如果是第一次，且无左右子树 则直接 操作
                 *           如果是第二次，且 忘了，下午书到了在看书
                 */
            }
        }
    }

}
