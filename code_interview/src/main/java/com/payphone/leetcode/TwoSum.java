package com.payphone.leetcode;

import java.util.HashMap;

/**
 * 两数之和
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TwoSum {

    // for 循环暴力
    public static int[] solution1(int[] nums, int target) {
        for (int out = 0; out < nums.length; out++) {
            for (int in = 0; in < nums.length; in++) {
                if (nums[out] + nums[in] == target && out != in) {
                    int[] result = {out, in};
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * 散列表 存放数的值 和 其对应的下标
     * target - nums中的数 然后 在hash中寻找result是否存在。
     * 时间复杂度 O（N）  空间复杂度O（N）
     * PS:每个数只能用一次，所以用完一次后需要从散列表中移除，只需要移除一个元素！
     * @param nums
     * @param target
     */
    public static int[] solution2(int[] nums, int target) {
        HashMap<Integer, Integer> table = new HashMap<>();
        // 初始化散列表 重复的数会被覆盖 但是没什么问题。会存在答案的。如果是[3,3] 6这种情况 也是没有问题的。
        for (int i = 0; i < nums.length; i++) {
            table.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            // target - nums[i] = 要寻找的那个值
            // table中包含那个值的话 就获取它的value
            table.remove(nums[i],i);
            int otherNumber = target - nums[i];
            if (table.containsKey(otherNumber)) {
                int[] result = {i, table.get(otherNumber)};
                return result;
            }
        }

        return null;

    }

    public static void main(String[] args) {
        int[] nums = {3, 3};
        int[] ints = TwoSum.solution2(nums, 6);
        System.out.println(ints[0]);
    }
}
