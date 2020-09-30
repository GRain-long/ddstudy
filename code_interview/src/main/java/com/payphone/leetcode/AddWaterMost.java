package com.payphone.leetcode;

public class AddWaterMost {
    public int maxArea(int[] height) {
        return fun1(height);
    }

    /**
     * 双重for暴力求解
     *
     * @return
     */
    public static int fun1(int[] height) {
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int area = getArea(i, j, Math.min(height[i], height[j])); // 求最大的面积
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }

    public static int getArea(int start, int end, int minHeight) {
        return (end - start) * minHeight;
    }
}
