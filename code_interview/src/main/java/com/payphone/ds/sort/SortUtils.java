package com.payphone.ds.sort;

/**
 * 排序代码 第一版
 * 仅针对数字
 */
public class SortUtils {

    /**
     * 直接插入排序
     * 思想：
     * 类似于玩纸牌时整理自己的纸牌
     * 依次把数据插入到一个已经拍好序的序列中
     * 假设第一个数有序，那么我们需要把第二个数插进去，且保证插入后的序列仍然有序。
     * <p>
     * 假设数据要求从小到大。后面的数大于前面的数，则数据不动，指针后移
     * 后面的数小于前面的数，则小的数后移，
     * 排序代码 或者说排序 是我最弱的一项，这个我只能意会，不好文字表述。
     * @param arr
     */
    public static void straightInsertion(int[] arr) {
        int val = -1;
        // 假设第一个有序，则要进行 length-1轮插入
        for (int i = 1; i < arr.length; i++) {
            val = arr[i]; // 记录待插入的值
            // 第一轮从index开始 向前检索
            for (int j = i; j > 0; j--) {
                // 有序 ，此轮插入结束
                if (val > arr[j - 1]) {
                    arr[j] = val;
                    break;
                } else {
                    arr[j] = arr[j - 1]; // 前面的数大，则前面的数后移
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] s = {1, 23, 4, 66, 567, 678, 89, 7856, 46};
        straightInsertion(s);
        for (int i = 0; i < s.length; i++) {
            System.out.print(s[i] + "\t");
        }
    }

}
