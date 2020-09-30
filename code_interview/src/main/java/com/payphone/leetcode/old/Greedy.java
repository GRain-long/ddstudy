package com.payphone.leetcode.old;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 贪心算法入门 每次挑最好的 举两个简单的例子
 * 1.课程安排问题 已知有 A B C D E F六门课，
 * 		已知每门课的上课时间和下课时间，
 * 		请将尽可能多的课程安排在同一间教室进行
 *
 * 2.背包问题（后期换DP解决）
 * 		 已知有N件物品，每件的价值和重量已知 小偷的背包承重有限，
 * 		 求小偷可偷取的价值最多的商品
 *
 * 3.集合覆盖问题
 * 		有心情再来写

 先自己思考，写出代码，在看给出的代码

 从明天开始到月底 ==> 更新动态规划和k近邻算法【超级简陋版】

 marked访问数组真好用。
 * @author 69546
 *
 */
public class Greedy {
    /**
     * 为了让尽可能多的课在同一间教室进行，每次挑选结束最早的那门课 先遍历结束时间数组， 找出最早结束的课程A，置为以安排，加入队列
     * 在遍历开始时间数组，找出最早开始且，时间在A后面的，置为已安排 重 复上述操作
     */
    public static void main(String[] args) {
        getMaxClazz();
        bag(80);
    }
    public static void getMaxClazz() {
        // 开始时间
        double[] startTime = {9, 9.5, 10, 10.5, 11};
        // 结束时间
        double[] endTime = {10, 10.5, 11, 11.5, 12};
        // 是否被访问
        boolean[] marked = new boolean[endTime.length];
        // 课程顺序
        Queue<Integer> queue = new LinkedList<Integer>();
        int min = 0;
        // 先找到开始课程
        for (int i = 0; i < endTime.length; i++) {
            if (endTime[i] <= endTime[min]) {
                min = i;
            }
        }
        marked[min] = true;
        queue.add(min);
        while (true) {
            // 满足条件的 最早结束的时间
            double minVal = Double.MAX_VALUE;
            // 该课程对应的索引
            int clazz = -1;
            // 找出开始时间大于endTime[min]的，且结束时间最早的
            for (int index = 0; index < startTime.length; index++) {
                if (!marked[index] && startTime[index] >= endTime[min]
                        && endTime[index] < minVal) {
                    minVal = endTime[index];
                    clazz = index;
                }
            }
            // 如果没找到说明不存在，退出。
            if (clazz == -1)
                break;

            min = clazz;
            marked[clazz] = true;
            queue.add(clazz);

        }
        // 输出课程
        while (!queue.isEmpty()) {
            Integer index = queue.poll();
            System.out.println(startTime[index] + "==>" + endTime[index]);
        }
    }

    // 背包问题 每次选可以容纳的最贵的商品
    public static void bag(int maxWeight) {

        int[] weight = {5, 12, 36, 50, 15};
        int[] value = {1000, 200, 3650, 58,500};
        boolean[] marked = new boolean[weight.length];
        Queue<Integer> queue = new LinkedList<Integer>();
        int best = 0;// 最佳偷取物品的id

        while(true) {
            int max = 0;
            for (int i = 0; i < weight.length; i++) {
                if (!marked[i] && weight[i] < maxWeight && value[i] > max) {
                    best = i;
                    max = value[i];
                }
            }

            //重量不足，不能再偷东西了。max就不会被赋值
            if(max == 0) break;
            maxWeight -= weight[best]; marked[best] = true;
            queue.add(best);
        }
        //输出偷东西的顺序
        while (!queue.isEmpty()) {
            Integer index = queue.poll();
            System.out.println(value[index]+"=="+weight[index]);
        }

    }
}
