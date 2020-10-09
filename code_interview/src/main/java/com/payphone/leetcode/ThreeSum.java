package com.payphone.leetcode;

import java.util.*;

public class ThreeSum {

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = threeSum.fun2(nums);


    }

    /**
     * 三重for  暴力求解
     */
    public List<List<Integer>> fun1(int[] nums) {
        int len = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(nums[i]);
                        tmp.add(nums[j]);
                        tmp.add(nums[k]);
                        result.add(tmp);
                    }
                }
            }
        }
        return result;
    }


    /**
     * 先排序在双指针
     */
    public List<List<Integer>> fun2(int nums[]) {
        List<List<Integer>> list = new ArrayList<>();
        // 先排序 默认升序
        Arrays.sort(nums);
        // 边界条件
        if (nums[0] > 0 || nums.length < 3) return list;


        for (int i = 0; i < nums.length - 2; i++) {
            int fixed = nums[i];
            // 内层for 双指针找符合条件的值
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                if (fixed + nums[start] + nums[end] == 0) {  //  符合条件加入
                    list.add(addToList(fixed, nums[start], nums[end]));
                    // 排除重复的
                    while (start < end && nums[start] ==nums[start + 1])start++;
                    while (start < end && nums[end] == nums[end - 1]) end--;
                } else if (fixed + nums[start] + nums[end] < 0) {
                    start++;
                } else if (fixed + nums[start] + nums[end] > 0) {
                    end--;
                } else {
                    start++;
                }
            }
            while (nums[i] == nums[i + 1]) i++;
        }
        return list;
    }

    private List<Integer> addToList(int... nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }
        return list;
    }
}
