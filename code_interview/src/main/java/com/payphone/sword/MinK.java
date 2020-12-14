package com.payphone.sword;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 最小的k个数
 */
public class MinK {

    /**
     * 小顶堆。把所有数据都存小顶堆里，然后从堆顶拿出指定k个小的数据，
     * 需要注意特殊情况。
     * input.length < k 返回空数组
     *
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution1(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        if (input.length < k) return list;
        for (int i = 0; i < input.length; i++) {
            queue.add(input[i]);
        }
        while (k > 0 && !queue.isEmpty()) {
            k--;
            list.add(queue.poll());
        }
        return list;
    }


    /**
     * 这种要考虑的极端情况比较多。
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution2(int[] input, int k) {

        ArrayList<Integer> list = new ArrayList<>();
        if (k == 0 || input == null || input.length < k) return list;
        // 大顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
            return o2 - o1;
        });
        for (int i = 0; i < input.length; i++) {
            if (queue.size() < k) {
                queue.add(input[i]);
            } else if (queue.peek() > input[i]) {
                queue.poll();
                queue.add(input[i]);
            }
        }
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            list.add(queue.poll());
        }
        return list;
    }

    public static void main(String[] args) {
        MinK minK = new MinK();
        int[] input = {4, 5, 1, 6, 2, 7, 3, 8};
        ArrayList<Integer> arrayList = minK.GetLeastNumbers_Solution1(input, 10);
        System.out.println(arrayList);
    }
}
