package com.payphone.sword;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author payphone
 * @date 2020/6/22 12:57
 * @Description 获得二叉树中的下一个结点
 * <p>
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 */
public class GetNextInBinaryTree {

    private ArrayList<TreeLinkNode> list = new ArrayList<>();

    /**
     * 法一：
     * 给定二叉树的一个结点，找出中序遍历下pNode的下一个结点。
     * 解题：先找到根节点，然后中序遍历，找pNode的下一个结点
     * 递归中序遍历二叉树，记录遍历的序列。然后扫描一遍序列找出pNode的下一个结点。
     */
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        TreeLinkNode root = pNode;
        while (root.next != null) {
            root = root.next;
        }
        inorder(root);
        for (int i = 0; i <list.size()-1 ; i++) {
            if(pNode==list.get(i)){

                return list.get(i+1);
            }
        }
        return null;
    }

    /**
     * 中序遍历递归写法  左 根 右
     * @param root 根结点
     * @return
     */
    private void inorder(TreeLinkNode root) {
        if (root == null) return;
        inorder(root.left);
        list.add(root);
        inorder(root.right);
    }

    /**
     * 法二：非递归解法
     */
    public TreeLinkNode GetNext2(TreeLinkNode pNode){
        int val = pNode.val;//要找的点
        boolean flag = false;
        //找到根
        while(pNode.next!=null){
            pNode = pNode.next;
        }
        Stack<TreeLinkNode> stack = new Stack<TreeLinkNode>();
        // 中序遍历非递归写法。左根右 左子树
        // 左子树一直入栈。无左子树的时候，栈中弹出一个元素，找这个元素的右子树。
        while(pNode!=null || !stack.isEmpty()) {
            while(pNode!=null) {
                stack.push(pNode);
                pNode = pNode.left;
            }
            if(!stack.isEmpty()) {
                pNode = stack.pop();
                if(flag)
                    return pNode;
                if(pNode.val == val)
                    flag = true;
                pNode = pNode.right;
            }
        }
        return null;
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
