package com.payphone.leetcode.old.graph;

import java.util.Arrays;

/***
 * 克鲁斯卡算法 按权值从小到大的顺序选择n-1条边，且不能构成回路 边的数目是一定的，不会变的。 要考虑的是不能构成回路， 且被选择了的边不能在进行选择
 * 回路问题可以用并查集进行解决
 *
 * 权值问题很好解决，进行排序就行 回路问题：并查集，如果两个二叉树有同一个根结点，那么就形成了回路
 *
 * @author 69546
 * 基本没有没用到Java的一些库，所以移植到C/C++很方便
 */
@SuppressWarnings("all")
public class KruskalMST {
    private int edgeNum = 0;// 边数 为定义边集数组做准备
    private char[] vertex;// 顶点数组，用于存储顶点信息
    private int[][] matrix;// 邻接矩阵，准备好边与边的信息，为边集数组做准备
    private final static int M = 10000;//定义的无穷大

    //初始化邻接矩阵，同时计算有多少条边
    public KruskalMST(char[] vertex, int[][] matrix) {
        int verNum = vertex.length;
        this.vertex = new char[verNum];
        this.matrix = new int[verNum][verNum];
        // 初始化顶点
        for (int i = 0; i < verNum; i++) {
            this.vertex[i] = vertex[i];
        }
        // 初始化矩阵邻接矩阵
        for (int i = 0; i < verNum; i++) {
            for (int j = 0; j < verNum; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        //统计边数目。因为自己到自己的不算边所以j从i+1开始
        for (int i = 0; i < verNum; i++) {
            for (int j = i + 1; j < verNum; j++) {
                //点与点之间有路径才算边数
                if (matrix[i][j] != M)
                    edgeNum++;
            }
        }

    }

    //打印矩阵
    public void showMatrix() {
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.print(this.matrix[i][j] + "\t");
            }
            System.out.println();
            System.out.println();
        }
    }

    // 准备工作 将图中的边存移植到EdgeData数组中
    public EdgeData[] getEdges() {
        int index = 0;
        EdgeData[] edges = new EdgeData[edgeNum];
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i + 1; j < vertex.length; j++) {
                if (matrix[i][j] != M) {
                    edges[index++] = new EdgeData(vertex[i], vertex[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        // 克鲁斯卡尔算法的邻接矩阵
        int matrix[][] = {
                /* A *//* B *//* C *//* D *//* E *//* F *//* G */
                /* A */ {0, 12, M, M, M, 16, 14},
                /* B */ {12, 0, 10, M, M, 7, M},
                /* C */ {M, 10, 0, 3, 5, 6, M},
                /* D */ {M, M, 3, 0, 4, M, M},
                /* E */ {M, M, 5, 4, 0, 2, 8},
                /* F */ {16, 7, 6, M, 2, 0, 9},
                /* G */ {14, M, M, M, 8, 9, 0}
        };
        KruskalMST ks = new KruskalMST(vertex, matrix);
        //选最短，且不构成回路的边生成最小生成树
        ks.kruskal();
    }

    //对候选边集进行排序
    public void sortEdge(EdgeData[] edges) {
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EdgeData temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    //获得顶点ch对应顶点数组中的下标
    public int getPosition(char ch) {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == ch)
                return i;
        }
        return -1;
    }

    /**
     * @param parent 父亲数组
     * @param i      获得i的父亲结点的数组下标
     * @return 如果没有父亲结点就返回自己的索引下标
     */
    public int getParent(int[] parent, int i) {
        while (parent[i] != 0)
            i = parent[i];
        return i;
    }


    public void kruskal() {
        //最后结果的数组起始索引
        int index = 0;
        //存储每个结点的父亲结点 初始化父亲结点为0，即不存在父亲。可以减少一次初始化过程
        //如果按并查集的思路一般是array[i] = i; 自己是自己的父亲
        int[] parent = new int[vertex.length];
        //创建结果数组 保存最小生成树的顶点和权值信息 结果集合大小是 vertex.length-1
        EdgeData[] result = new EdgeData[vertex.length - 1];
        //通过遍历邻接矩阵获得边集合
        EdgeData[] edges = getEdges();
        //对边集信息进行排序
        sortEdge(edges);
        //遍历edges数组 将權值最小且连接后不构成回路的边加入最小生成树
        for (int i = 0; i < edgeNum; i++) {
            int p = getPosition(edges[i].start);
            int q = getPosition(edges[i].end);
            int pRoot = getParent(parent, p);
            int qRoot = getParent(parent, q);
            //如果父节点不一样
            if (pRoot != qRoot) {
                parent[pRoot] = qRoot;
                result[index++] = edges[i];
            }
        }
        System.out.println(Arrays.deepToString(result));
    }
}

class EdgeData {
    char start;
    char end;
    int weight = 0;

    public EdgeData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EdgeData [start=" + start + ", end=" + end + ", weight="
                + weight + "]";
    }

}
