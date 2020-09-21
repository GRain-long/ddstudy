package com.payphone.sword;

/**
 * @author payphone
 * @date 2020/6/21 11:49
 * @Description 找出数组中的重复数字 但不可修改数组
 * 数组长度n+1 元素的值在1~n之间，不修改数组的内容，找出重复的数字
 * <p>
 * 此题不好测试，牛客没有对应的题。散列表的解题思路最简单粗暴。效率也不低。
 * 法二的解法较为灵活，是对二分查找的灵活运用，多加体会！
 */
public class DuplicateNumber2 {

    public static void main(String[] args) throws Exception {
        DuplicateNumber2 dd = new DuplicateNumber2();
        // 长度n+1 元素 1~n
        // 5  1~4
        int[] numbers = {1, 3, 2, 4, 4};
        int[] result = {-1};
        boolean duplicate = dd.duplicate2(numbers, numbers.length, result);
        System.out.println(duplicate + ":" + result[0]);
    }


    /**
     * 法一：核心：散列表 判重。
     * 遍历数组，散列表中不存在，则标记，用于之后的判重。
     * 若散列表中存在相同的数字 说明数字重复了！
     * 若遍历完数组，散列表中未发现重复的，则说明无重复数字。
     */
    public boolean duplicate1(int numbers[], int length, int[] duplication) {
        if (length <= 0) return false;
        int[] HashTable = new int[length + 1];
        for (int i = 0; i < length; i++) {
            int curNumber = numbers[i];
            // 如果散列表中没有发现该数字，则当前数字设置为已发现
            if (HashTable[curNumber] == 0) {
                HashTable[curNumber] = 1;
            } else {
                // 否则发现该数组重复，返回true
                duplication[0] = curNumber;
                return true;
            }
        }
        return false;
    }


    // 看法二前 先复习下二分查找 架设数组数据从小 到 大排序
    public int binarySearch(int[] numbers, int target) {
        int start = 0;
        int end = numbers.length - 1;
        while (start <= end) {
            int mid = ((end - start) >> 1) + start;
            if (numbers[mid] == target) return mid;
            else if (numbers[mid] < target) {
                // 小于 target向右边找
                start = mid + 1;
            } else if (numbers[mid] > target) {
                // 大于 target向左边找
                end = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 法二：数组元素在1~n之间，如果有重复的元素，则某个元素会出现两次。
     * 我们用二分查找的思路，将数字的取值范围1~n进行二分查找。
     * 1. 统计数组中 元素∈1~n/2的有几个。如果总数＞n/2说明重复的数字的取值∈1~n/2，反之则不存在，继续统计n/2+1~n范围的。
     * 继续二分查找
     * 2. 统计数组中 元素∈(n/2)+1 ~ n 的有几个。如果 总数 > n - (n/2+1)+1 则说明重复的在这里面。
     */
    public boolean duplicate2(int numbers[], int length, int[] duplication) throws Exception {
        if (length <= 0) return false;
        int start = 1;
        int end = length - 1;

        // 重复的数字是否在这个范围内
        boolean belong = false;

        while (start <= end) {
            int mid = ((end - start) >> 1) + start;
            belong = belongRange(start, mid, numbers);
            if (belong) {
                if (start == end) {
                    duplication[0] = numbers[start];
                    return true;
                }
                // 继续左边二分找  注意这边不能是end = mid-1。因为这样会忽略mid这个取值，我们无法确定mid就不是重复的数字
                end = mid;
            } else {
                // 否则右边二分找
                start = mid + 1;
            }
        }
        return false;
    }

    // 统计 数组中 start~mid 出现的次数
    private boolean belongRange(int start, int mid, int[] numbers) {
        int count = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (start <= numbers[i] && numbers[i] <= mid) {
                count++;
            }
        }
        // 判断重复的数字是否是在start~end之间
        if (count > mid - start + 1) return true;
        return false;
    }


}
