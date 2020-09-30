package com.payphone.leetcode.old;

import java.util.PriorityQueue;

public class HuffmanTree {
    public static void main(String[] args){
        int []arr = {13,7,8,3,29,6,1};
        createHuffmanTree(arr);
    }


    /**
     * 回忆手动计算哈夫曼树的过程
     * 找两个最小的，把他们合并。然后把合并后的结点添加进去，再找两个最小的，直到只有一个结点了
     * 如 2 2 6 8
     * 2 2 合并成4 然后变成了 4 6 8
     * 4 6合并 然后变成了 10 8
     * 10 8 合并 变成了 18 只有一个结点了结束
     * 我就偷懒，直接调库了。

     不知道为什么韩不用优先队列。。。
     扩展：堆排的时间复杂度是O(NlongN)为什么排序不喜欢用它，喜欢用快排？
     因为堆排不满足程序的局部性原理。它不是和相邻的元素比较交换。
     具体请查看算法四优先队列那块内容
     * */
    public static void createHuffmanTree(int[]arr){
        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        for(int value:arr){
            queue.add(new Node(value));
        }
        while(queue.size()>1) {
            //找两个最小的
            Node left = queue.poll();
            Node right = queue.poll();
            //合并成父节点
            Node parent = new Node(left.val+right.val);
            parent.left = left;
            parent.right = right;
            //把新结点加进去
            queue.add(parent);
        }
        Node root = queue.poll();
        preOrder(root);
    }

    /**
     * @param node
     */
    public static void preOrder(Node node) {
        if(node!=null) {
            System.out.println(node.val);
            preOrder(node.left);
            preOrder(node.right);
        }
    }
}

class Node implements Comparable<Node>{
    int val;
    Node left;
    Node right;

    public Node(int val){
        this.val = val;
    }
    @Override
    public int compareTo(Node o){
        //从小到大排序
        return this.val - o.val;
    }
    @Override
    public String toString(){
        return this.val+"\t";
    }
}
