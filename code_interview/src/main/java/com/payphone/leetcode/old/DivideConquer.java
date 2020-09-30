package com.payphone.leetcode.old;

/**
 *习题一：
 * 一块土地用正方形铺满，求正方形的最大面积是多少
 * 如 80*60的土地
 * 可以用20*20的方块进行填充
 * 请使用分治法来做。本质是求最大公因子。
 *
 *
 * 习题二：
 * 递归求数组的和
 *
 *
 *
 * 习题三：
 * 实现快排，并给出快排的优化策略
 * 下面的代码是我见过的比较好的快排代码之一。
 * 具体分析步骤参看算法四快排部分
 *
 *
 *
 * PS：二叉树的繁茂程度代码 我晚上写。。。。顺带看看刷题网站上有没有这个题，看看能不能通过测试用例。
 */
@SuppressWarnings("all")
public class DivideConquer {
    public static void main(String[] args) {
        DivideConquer maxS = new DivideConquer();
    }
    /**
     * 	找出可划分的最大正方形和剩余需要划分的矩形，对剩余的矩形进行划分
     * 	当widht==high时，说明划分结束
     * @param width
     * @param high
     * @return
     */
    public int getLength(int width,int high) {
        if(width==high) {
            return width;
        }else{
            if(width>high)
                return getLength(width-high, high);
            else
                return getLength(high-width,width);
        }
    }

    /**
     * 用分治法进行求和
     * @param array
     * @return
     */
    private int index = 0;
    public int sum(int[]array) {
        //数组为空 返回第一个元素
        if(array.length==1)
            return array[0];
            // 终止条件 数组的元素都加完了，再加一个0 不影响
        else if(array.length==index)
            return 0;
        else
            // 循环条件 index处的元素加上剩余的元素
            return array[index++]+=sum(array);
    }



    /**
     * 分治法实现快排
     * 调用快排前，先把元素打乱，再进行排序，这种开销很值得，可以保证快排的效率
     * @param array
     */
    public void quick(int []array, int lo, int hi) {
        if (hi <= lo)
            return;
        int mid = paratition(array, lo, hi);
        quick(array, lo, mid - 1);
        quick(array, mid + 1, hi);
    }

    /**
     * @param array
     * @param lo 低位
     * @param hi 高位
     * @return
     */
    public int paratition(int[]array,int lo,int hi) {
        int i = lo,j = hi+1;
        //为什么要j = hi+1 ？ j = hi,while循环中j--不行吗？
        //不行。j--的话，假设j = 5; j--找到了不满足条件的元素array[5] 然后j变成了4.。。

        int v = array[lo];//中枢的选取

        while(true) {
            //找到一个左边比中枢大的不合条件的数
            while(array[++i]<v)
                if(i==hi) break;//从头到尾都没找到说明有序 退出
            //找到一个右边边比中枢下的不合条件的数
            while(array[--j]>v)
                if(j==lo) break;
            //终止条件 两侧区域划分好了，但是i j探测越界。具体参看下面的注释
            if(i>=j) break;

            //交换这两个不合格的数
            int temp = array[i]; array[i] = array[j]; array[j] = temp;
        }

        //把中枢放到正确的位置 j是正确的位置 因为，当某一侧的数都小于mid，另一侧的数都大于mid时，i为了找到不满足
        //情况的条件会找到右侧（都大于mid的那块区域），j为了找到满足条件的数会找到左侧（都小于mid的区域）
        //请画图
        //	23 50 10 99 18 23 98 84
        //	以23为中枢，头尾两侧找不满足条件的数
        //	左侧不满足的第一个数是50 右侧是 18 交换
        // 	23 18 10 99 50 23 98 84
        //继续找 i最终指向99 j最终指向10
        //中枢与10交换，第一次划分结束

        int temp = array[j]; array[j] = array[lo]; array[lo] = temp;
        return j;
    }
}

/**
 无注释版快排的划分代码
 public int paratition(int[]array,int lo,int hi) {
 int i = lo,j = hi+1;
 int v = array[lo];
 while(true) {
 while(array[++i]<v)
 if(i==hi) break;
 while(array[--j]>v)
 if(j==lo) break;

 if(i>=j) break;

 int temp = array[i]; array[i] = array[j]; array[j] = temp;
 }
 int temp = array[j];
 array[j] = array[lo];
 array[lo] = temp;
 return j;
 }
 *
 * */
