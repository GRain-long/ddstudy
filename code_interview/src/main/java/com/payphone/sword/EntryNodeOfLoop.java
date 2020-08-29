package com.payphone.sword;

import java.util.HashMap;

/**
 * 题目描述
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 * 题解 讨论 通过的代码笔记 纠错 收藏
 */
public class EntryNodeOfLoop {

    public ListNode test(ListNode pHead) {
        HashMap<ListNode, Integer> table = new HashMap<>();
        ListNode cur = pHead;
        while (cur != null) {
            if (table.containsKey(cur)) { // 重复，说明有环，返回入口节点，即重复的那个节点
                return cur;
            } else {
                table.put(cur, 1);
                cur = cur.next;
            }
        }
        return null;
    }
}
