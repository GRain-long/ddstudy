package com.payphone.leetcode.old.graph;

/**
 * 算法思想
 * 很简单，贪心的思想
 * 从一个顶点V出发，求V到其余各点的最短路径
 * 注意：这个最短路径不要求它要经过所有的点。注意与prim算法进行区分
 * 这个算法有个严格的证明可以证明贪心算法的正确性
 * 假设V-->W的最小权值是disTo[W]
 * 利用反证法证明其必要性
 * 假设disTo[w]是V到W的最短路径
 * 如果对于某条从v到w的边e有
 * disTo[w]>disTo[v]+e.weight
 * 那么从v到w（经过o）的路径长度必然小于disTo[w].
 * 于disTo是按权值从小到大收录点相矛盾。
 * <p>
 * 充分性公式太长 不写了。PS 大多数书都只给出了必要性的证明
 * <p>
 * 多体会下数据结构的设计和我变量的定义【参考的算法四P417页。有书的可以去看一下】
 */

@SuppressWarnings("all")
public class Dijkstra {
    private static final double M = Double.MAX_VALUE;// 无穷大
    private double[] distTo;// 假设源点为s distTo[v]是s到v的最短路径长度
    private int[] edgeTo;// edgeTo[v]
    // 是v的上一个节点是谁。类比并查集，这样可以找到最终的父节点。从子孙到祖先这一查找路径反过来就是最短路径的轨迹了
    private double[] weight;
    private int[] vertex;// 访问数组
    private double[][] matrix;//矩阵

    public static void main(String[] args) {
        int[] vertex = {0, 1, 2, 3, 4};
        double[][] matrix = {
                {M, 10, M, 30, 100},
                {M, M, 50, M, M},
                {M, M, M, M, 10},
                {M, M, 20, M, 60},
                {M, M, M, M, M}
        };
        Dijkstra dijkstra = new Dijkstra(vertex, matrix);
        dijkstra.initdistTo(0);
        dijkstra.dijkstra(0);
        String pathTo = dijkstra.pathTo(4);
        System.out.println(pathTo);
    }

    /**
     * 算法主体
     */
    public void dijkstra(int v) {
        // 标记v为已经找到最短路径
        distTo[v] = 0;
        edgeTo[v] = -1;
        int count = 1;
        // 如果还有没有找到的最短路径
        while (count < vertex.length) {
            int cur = 0;
            // 查看v到其他点的距离
            for (int i = 0; i < matrix[v].length; i++) {
                cur = findMinDistTo();
            }
            // 以cur为中转点，看是否需要更新distTo的权值
            for (int i = 0; i < matrix[v].length; i++) {
                if (distTo[i] > distTo[cur] + matrix[cur][i]) {
                    //发现以cur为中转点到v的距离更近
                    distTo[i] = distTo[cur] + matrix[cur][i];// 更新最短路径的权值 回忆手动计算
                    edgeTo[i] = cur;
                }
            }
            weight[cur] = distTo[cur];
            System.out.println(v + "==》" + cur + " length=" + distTo[cur]);
            distTo[cur] = 0;
            count++;
        }
    }

    /**
     * 查找距离源点最近的那个点 回忆手工计算
     *
     * @return
     */
    public int findMinDistTo() {
        double min = M;
        int index = 0;
        for (int i = 0; i < distTo.length; i++) {
            if (distTo[i] != 0 && distTo[i] < min) {
                min = distTo[i];
                index = i;
            }
        }
        return index;
    }

    /**
     * 初始化distTo数组
     */
    public void initdistTo(int v) {
        for (int i = 0; i < matrix[v].length; i++) {
            distTo[i] = matrix[v][i];
        }
    }

    /**
     * 初始化 顶点，边权值信息
     *
     * @param vertex
     * @param matrix
     */
    public Dijkstra(int[] vertex, double[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
        this.distTo = new double[vertex.length];
        this.edgeTo = new int[vertex.length];
        this.weight = new double[vertex.length];
    }

    /**
     * 输出路径
     */
    public String pathTo(int v) {
        String s = "" + v;
        while (edgeTo[v] != -1) {
            s += edgeTo[v];
            v = edgeTo[v];
        }
        return new StringBuffer(s).reverse().toString();
    }
}
