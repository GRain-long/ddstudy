package com.payphone.sword;

import java.util.Arrays;

/**
 * @author payphone
 * @date 2020/6/21 11:49
 * @Description 找出数组中的重复数字
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。
 * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，
 * 那么对应的输出是第一个重复的数字2。
 * <p>
 * 题目链接 <a href="https://www.nowcoder.com/practice/623a5ac0ea5b4e5f95552655361ae0a8?tpId=13&&tqId=11203&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking"></a>
 */
public class DuplicateNumber {

    public static void main(String[] args) {
        DuplicateNumber duplicateNumber = new DuplicateNumber();
        int[] numbers = {2, 3, 4, 5, 5, 6, 7, 6, 5};
        int[] result = {-1};
        boolean duplicate = duplicateNumber.duplicate3(numbers, numbers.length, result);
        System.out.println(duplicate + ":" + result[0]);
    }

    /**
     * 法一：先排序 再查找
     * number 为传入的数组 length为数字长度  duplication存储重复的那个数字
     * 返回值为 是否存在重复数字 存在返回true 不存在放回false
     */
    public boolean duplicate(int numbers[], int length, int[] duplication) {
        // 非法输入判断
        if (length <= 0) return false;
        Arrays.sort(numbers);
        for (int i = 0; i < length - 1; i++) {
            if (numbers[i] == numbers[i + 1]) {
                duplication[0] = numbers[i];
                return true;
            }
        }
        return false;
    }

    /**
     * 法二：核心：散列表 判重。
     * 遍历数组，散列表中不存在，则标记，用于之后的判重。
     * 若散列表中存在相同的数字 说明数字重复了！
     * 若遍历完数组，散列表中未发现重复的，则说明无重复数字。
     */
    public boolean duplicate2(int numbers[], int length, int[] duplication) {
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

    /**
     * 法三：数组特点。数组长度为n 数字范围为0~n-1
     * 将数字n放在索引为n的地方。不同的数字放的位置不一样。如果出现两个数组都需要
     * 放在同一个未知，则说明该数字重复。
     */
    public boolean duplicate3(int numbers[], int length, int[] duplication) {
        if (length <= 0) return false;
        // 注意 只有 i == array[i]的时候索引才自增
        for (int i = 0; i < length; ) {
            int curNumber = numbers[i];
            // 索引i = array[i]索引后移
            if (i == curNumber) {
                i++;
            } else if (curNumber == numbers[curNumber]) { //判断是否重复
                duplication[0] = curNumber;
                return true;
            } else { // 不重复则交换
                swap(i, curNumber, numbers);
            }
        }
        return false;
    }

    // 交换数组中索引1与索引2的数字
    public void swap(int index1, int index2, int[] number) {
        int temp = number[index1];
        number[index1] = number[index2];
        number[index2] = temp;
    }
}
