package com.payphone.leetcode;

import java.util.Stack;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AddTwoNumber {
    public static void main(String[] args) {

    }

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }

    /**
     * 理解题意：表头是低位，表尾是高位。  2-->3-->4  432 4是高位
     * 单纯的链表相加。 高位不足补0
     * PS:链表长度不一定一样长。高位补0
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode head = new ListNode(-1);
        ListNode curr = head;
        while (l1 != null || l2 != null) {
            int n1 = 0, n2 = 0;
            if (l1 != null) n1 = l1.val;
            if (l2 != null) n2 = l2.val;
            int result = l1.val + l2.val + carry;
            // 判断是否进位
            if (result >= 10) {
                carry = 1;
                result -= 10;
                addNode(curr, result);
            } else {
                addNode(curr, result);
                carry = 0;
            }

            l1 = l1.next;
            l2 = l2.next;
        }
        return head.next;
    }

    public static void addNode(ListNode curr, int val) {
        ListNode listNode = new ListNode(val);
        curr.next = listNode;
        curr = curr.next;
    }
}
