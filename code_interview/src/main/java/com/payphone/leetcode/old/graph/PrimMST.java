package com.payphone.leetcode.old.graph;

import java.util.Arrays;

public class PrimMST {
    public static void main(String[] args) {
        // 测试代码
        MST mst = new MST();
        int M = mst.MAX;
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verNumber = vertex.length;
        int[][] edge = {
                {M, 5, 7, M, M, M, 2},
                {5, M, M, 9, M, M, 3},
                {7, M, M, M, 8, M, M},
                {M, 9, M, M, M, 4, M},
                {M, M, 8, M, M, 5, 4},
                {M, M, M, 4, 5, M, 6},
                {2, 3, M, M, 4, 6, M}
        };
        Graph graph = new Graph(verNumber);
        mst.createGraph(graph, verNumber, vertex, edge);
        mst.show(graph);
        mst.prim(graph, 'B');

    }

    static class MST {
        private int MAX = 10000;

        // 创建图
        public void createGraph(Graph g, int vertexNumber, char[] vertex,
                                int edge[][]) {
            if (g == null)
                return;
            if (vertexNumber < 0)
                System.out.println("Exception");
            for (int i = 0; i < vertexNumber; i++) {
                // 给顶点赋值
                g.vertex[i] = vertex[i];
            }
            // 初始化边的权值
            for (int i = 0; i < vertexNumber; i++) {
                for (int j = 0; j < vertexNumber; j++) {
                    g.edge[i][j] = edge[i][j];
                }
            }
        }

        // 显示图的邻接矩阵
        public void show(Graph g) {
            if (g == null)
                return;
            for (int i = 0; i < g.edge.length; i++) {
                System.out.println(Arrays.toString(g.edge[i]));
                System.out.println();
            }
        }

        public int getPosition(Graph g, char v) {
            for (int i = 0; i < g.verNum; i++) {
                if (g.vertex[i] == v)
                    return i;
            }
            return -1;
        }

        /**
         * @param g 图对象【结构体】
         * @param v 以顶点v为初始点进行树的构造
         */
        public void prim(Graph g, char v) {
            int count = 0;
            int vIndex = -1;//记录顶点v的数组索引
            boolean visited[] = new boolean[g.verNum];//访问数组 boolean数组初始值为false
            //获得顶点v在顶点数组中的下标
            vIndex = getPosition(g, v);
            if (vIndex == -1) {
                System.out.println("prim Exception");
                return;
            }
            //初始化v的访问数组为已经访问
            visited[vIndex] = true;
            int h1 = -1, h2 = -1, minWeight = MAX;
            for (int k = 1; k < g.verNum; k++) {//prim算法结束后会有n-1条边
                for (int i = 0; i < g.verNum; i++) {//我们假定i是被访问过的结点，求被访问过的结点到未被访问过的结点的最短距离
                    if (visited[i]) {//避免了多余得循环
                        for (int j = 0; j < g.verNum; j++) {
                            //最里面的两个for循环是求出来，已知的最小生成树中的点和未知点的最短的距离
                            //意思就是 MST AB两个点  集合 C D E三个点
                            //双重for循环求出了 A 到 C D E的距离
                            //           求出了B 到 C D E的距离
                            //			 用minWeigh记录 最短的距离 用h1记录是生成树中的点
                            //									 用h2记录是集合中那个点到h1的距离最短
                            if (!visited[j] && g.edge[i][j] < minWeight) {//如果j未被访问，就求j到已被访问点的最小的距离
                                minWeight = g.edge[i][j];
                                h1 = i;
                                h2 = j;
                            }//end if
                        }//end for inner
                    }//end for out
                }
                System.out.println(g.vertex[h1] + "-->" + g.vertex[h2]);
                count += minWeight;
                System.out.println(count);
                visited[h2] = true;
                minWeight = MAX;
            }
        }

    }

    static class Graph {
        int verNum;
        char[] vertex;// 顶点
        int[][] edge;

        public Graph() {
        }

        public Graph(int num) {
            this.verNum = num;
            vertex = new char[verNum];
            edge = new int[verNum][verNum];
        }

    }
}
