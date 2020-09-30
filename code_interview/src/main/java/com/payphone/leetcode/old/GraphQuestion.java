package com.payphone.leetcode.old;

import java.util.Arrays;
import java.util.LinkedList;

public class GraphQuestion {
    /**
     * DJS 刷题版
     */
    //精简版适用于刷题
    public void djs(int v, double[][] matrix) {
        int vertexNumber = matrix[v].length;
        double[] distTo = new double[vertexNumber];//v距离各点的权值
        int[] vertex = new int[vertexNumber];//顶点表
        double[] weights = new double[vertexNumber];//记录v-i的最终的最短权值
        //初始化distTo
        for (int i = 0; i < vertexNumber; i++) {
            distTo[i] = matrix[v][i];
        }
        distTo[v] = 0;
        weights[v] = 0;//初始化顶点
        int count = 0;
        while (count < vertexNumber - 1) {
            double min = Double.MAX_VALUE;
            int index = 0;
            //找最小的边 加入进行
            for (int i = 0; i < vertexNumber; i++) {
                if (distTo[i] != 0 && distTo[i] < min) {
                    min = distTo[i];
                    index = i;
                }
            }
            //更新权值
            for (int i = 0; i < vertexNumber; i++) {
                if (distTo[i] > distTo[index] + matrix[index][i]) {
                    distTo[i] = distTo[index] + matrix[index][i];
                }
            }
            weights[index] = distTo[index];
            distTo[index] = 0;
            count++;
        }
        System.out.println(Arrays.toString(weights));
    }

    public void primSimple(int v, double[][] matrix) {
        int len = matrix[v].length;
        double[] distTo = new double[len];
        boolean[] marked = new boolean[len];
        double minWeight = 0.0;

        LinkedList<Integer> queue = new LinkedList<Integer>();
        marked[v] = true;
        // 入队
        queue.addLast(v);
        for (int i = 0; i < len; i++) {
            distTo[i] = matrix[v][i];
        }
        while (queue.size() < len) {
            double min = Double.MAX_VALUE;
            int index = 0;
            // 找距离生成树最近的点
            for (int i = 0; i < len; i++) {
                if (distTo[i] != 0 && min > distTo[i]) {
                    index = i;
                    min = distTo[i];
                }
            }
            minWeight += distTo[index];
            distTo[index] = 0;
            marked[index] = true;
            // 入队，队列从后进去
            queue.addLast(index);
            for (int i = 0; i < len; i++) {
                if (!marked[i] && matrix[index][i] < distTo[i]) {
                    distTo[i] = matrix[index][i];
                }
            }
        }
        System.out.println(minWeight);
    }
}
