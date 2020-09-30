package com.payphone.leetcode.old;

public class ArraysDemo {

    public static void main(String[] args) {
        // fn1();
        int[] array = {10, 9, 2, 5, 3, 7, 101, 18, 23, 13, 34, 45, 6, 76, 88, 9, 9090, 90, 234, 234, 123};
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        reduce(array);
    }

    public static void reduce(int[] array) {
        int len = array.length - 1;
        // 用于存储当前索引为i的数字可构成的最大递减子序列的长度
        int[] temp = new int[array.length];
        temp[len--] = 1;
        // 保存最大长度，方便后期遍历
        int maxLen = 1;
        // 从后向前扫描，比较判断
        while (len >= 0) {
            int max = 1;
            for (int i = array.length - 1; i > len; i--) {
                if (array[len] > array[i] && max <= temp[i]) {
                    max = temp[i] + 1;
                }
            }
            // 最大长度
            if (maxLen < max) {
                maxLen = max;
            }
            temp[len] = max;
            len--;
        }
        // 输出结果
        System.out.printf("\n最大程度为：%d\n", maxLen);
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == maxLen) {
                System.err.printf("%d\t", array[i]);
                maxLen--;
            }
        }
    }
}