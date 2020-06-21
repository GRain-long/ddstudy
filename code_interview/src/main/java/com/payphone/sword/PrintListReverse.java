package com.payphone.sword;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author payphone
 * @date 2020/6/21 19:19
 * @Description 从尾到头打印链表
 */

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

public class PrintListReverse {


    /**
     * 法一：用辅助栈，利用栈先进后出的特性。顺序入栈，逆序输出
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = listNode;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }

        ArrayList<Integer> list = new ArrayList<>();
        while (stack.size() != 0) {
            list.add(stack.pop().val);
        }
        return list;
    }


    /**
     * 法二：先把链表逆置 再输出！
     * 链表的操作要十分小心！注意别断链！
     */
    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        ListNode dynamic = new ListNode(-1);
        // 链表头插法，每次插入后，原链表后面的元素会在新链表前面
        while (listNode != null) {

            // 获得需要摘除的结点
            ListNode t = listNode;
            // 全局指针后移到第二个结点
            listNode = listNode.next;

            // 头插法操作
            t.next = dynamic.next;
            dynamic.next = t;
        }
        ArrayList<Integer> list = new ArrayList<>();
        while (dynamic.next != null) {
            list.add(dynamic.next.val);
            dynamic = dynamic.next;
        }

        return list;
    }
}
