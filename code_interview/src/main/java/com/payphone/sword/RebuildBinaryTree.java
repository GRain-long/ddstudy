package com.payphone.sword;

/**
 * @author payphone
 * @date 2020/6/21 21:52
 * @Description 一道十分经典的，有难度的 分治法的题。 重建二叉树！难题！
 * <p>
 * 题目描述
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，
 * 则重建二叉树并返回。
 * 题目链接<a href="https://www.nowcoder.com/practice/8a19cbe657394eeaac2f6ea9b0f6fcf6?tpId=13&&tqId=11157&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking">重建二叉树</a>
 */
public class RebuildBinaryTree {
    /**
     * 这题相当有难度 我只给出一种解法，因为我只会这一种
     * 1.需要理解二叉树的遍历规则 及其特点1
     * 2.需要找出正确的递归终止添加！
     * 3.最好知道如何避免不必要的递归！[★★★★★]
     */
    public static void main(String[] args) {
        RebuildBinaryTree re = new RebuildBinaryTree();
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        re.reConstructBinaryTree(pre, in);
    }

    // pre 前序序列  in中序序列

    /**
     * 解题思路
     * 二叉树的遍历：
     * 前序遍历：根 左 右；最前面的是根结点，
     * 中序遍历：左 根 右：可以通过前序的结点，划分出 左右子树的结点集合
     * 然后递归创建
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        TreeNode reconstruct = reconstruct(pre, in, 0, pre.length - 1, 0, in.length - 1);
        return reconstruct;
    }

    /**
     * @param pre    前序序列数组
     * @param in     中序序列数组
     * @param pstart 前序的开始查找位置
     * @param pend   前序的结束位置
     * @param istart 中序的开始位置
     * @param iend   中序的结束位置
     * @return 二叉树
     */
    private TreeNode reconstruct(int[] pre, int[] in, int pstart, int pend, int istart, int iend) {
        if (pstart > pend) return null;
        if (istart > iend) return null;
        // 找到根结点
        int root = pre[pstart];
        // 找到根结点的左右子树群
        TreeNode treeNode = new TreeNode(root);
        int mid = -1;
        // 根据中序序列 找到划分左右子树的那个点 istart~点-1 是左子树群   点+1~end 是右子树群
        for (int i = istart; i <= iend; i++) {
            if (root == in[i]) {
                mid = i;
                break;
            }
        }
        // 可能左右子树只有一个有 可能都有 可能都没有 如何判断？

        // 中序中没有发现 则无 & 根结点在中序的最左边 则无左孩子
        if (mid == -1 || mid <= istart) {
            treeNode.left = null;
        } else {
            // 关键在于 start end的计算
            // left的很好计算 之前消耗了一个pre 所以 pre起始位置pstart + 1 结束位置pend
            //                               in的计算比较简单 for循环里进行了划分istart~mid - 1
            treeNode.left = reconstruct(pre, in, pstart + 1, pend, istart, mid - 1);
        }
        // 中序中没有发现 则无 & 根结点在中序的最右边 则无右孩子
        if (mid == -1 || mid >= iend) {
            treeNode.right = null;
        } else {
            // 关键在于 start end的计算
            // pre的start 计算非常麻烦
            // pre的start需要计算出 left消耗了多少前序的结点【递归建立，它把左子树群都建立好了才进行右子树建树的，左子树群建立的过程需要确定一系列的父节点，所以需要消耗pre中的点】。
            //                                left消耗的结点数目就是lef左子树群的结点总数 = mid-1-istart+1
            //                                保姆级解释：treeNode.left = reconstruct(pre, in, pstart + 1, pend, istart, mid - 1);
            //                                           这个递归进去了，会为treeNode接上一个完整的二叉树，而这个二叉树的结点总数是：它分到的左子树群的结点数目。
            //                                           这个结点数目就是 istart~点-1 即 istart~mid-1【mid是父节点的位置】
            //                                           所以消耗的结点数的计算公式是：mid-1-istart+1 = mid -start = len
            //                                           所以 pstart = pstart + len + 1 这个1是最上面 root消耗了一个pre的点。
            //                                 in的计算比较简单 for循环里进行了划分
            treeNode.right = reconstruct(pre, in, pstart + (mid - istart) + 1, pend, mid + 1, iend);
        }
        return treeNode;
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}

