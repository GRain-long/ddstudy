package com.payphone.sword;

/**
 * @author payphone
 * @date 2020/6/21 18:06
 * @Description 二维数组的查找。
 * <p>
 * 题目描述
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，
 * 判断数组中是否含有该整数。
 * </p>
 * <a href="https://www.nowcoder.com/practice/abc3fe2ce8e146608e868a70efebf62e?tpId=13&&tqId=11154&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking">题目链接</a>
 */
public class FindInArray {
    public static void main(String[] args) {

    }

    // 法一：暴力解，直接遍历
    public boolean Find(int target, int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 法二：根据数组特点，进行查找。
     * 不能用左上角的和右下角的为初始点。因为那两个点 是有两个方向可以走的，
     * 不唯一，加大了解题难度！
     * <p>
     * 用右上角或左下角为起始点进行查找。
     * 以右下角为例子。
     * 大于 number 向左边走。
     * 小于 number 向上走。
     * 就这样一直走下去，直到找到元素 或 元素不符合要求
     */
    public boolean Find2(int target, int[][] array) {
        if(array.length==0 || array[0].length==0) return false;
        int len = array.length;
        int row = len - 1, col = 0;
        while (row >= 0 && col < len) {
            if (array[row][col] > target) {
                // 当前元素大了 向上走
                row -= 1;
            } else if (array[row][col] < target) {
                // 当前元素笑了 向右走
                col += 1;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 看起来思路一很low 思路二很强是吧。
     * 你去牛客上测试 会发现 简单粗暴的解法速度比较稳定，
     * 那个看起来很强的速度直接的差距很大。【当然，极大可能是受服务器性能的影响】
     *
     * 这或许是数组寻址造成的。 电脑有个高速缓存的概念。每次会把一大片数据加载cache中。
     * 数组也是如此，例如：一次把这一行【假设数组按行存取】的数据都加载进去。
     * 发现这列没有我们要找的数据，又要     * 把其他的加载进来，这个操作是耗时的。
     * 法一需要加载的次数一般是更少的，法二的加载次数一般是更多的。
     * 所以用平摊分析的测试方式进行测试，可能会出现 法一比法二快不少的情况。
     *
     * 堆排序也存在这种情况。不符合程序的局部性原理【数据加载进cache】。
     */
}
