package com.payphone.leetcode.old;


import java.util.ArrayList;
import java.util.Stack;

public class LinkedListQuestion {

    /**
     * 期待已久的水题 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
     * 题目链接：https://www.nowcoder.com/practice/d0267f7f55b3412ba93bd35cfa8e8035?tpId=13&tqId=11156&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
     */
    // 最笨的办法。利用栈的特性。先全部入栈，在出栈。顺序正好反了。
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack<Integer> stack = new Stack<Integer>();
        while (listNode != null) {
            stack.add(listNode.val);
            listNode = listNode.next;
        }
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        while (!stack.empty()) {
            arrayList.add(stack.pop());
        }
        return arrayList;
    }

    // 第二种，链表逆转在依次加入List集合。空间复杂度更低？
    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        // 定义一个哑结点 方便操作
        ListNode head = new ListNode(-1);
        ListNode cur = listNode;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = head.next;
            head.next = cur;
            cur = temp;
        }
        head = head.next;
        while (head != null) {
            arrayList.add(head.val);
            head = head.next;
        }
        return arrayList;
    }

    /**
     * 经典面试题：输入一个链表，反转链表后，输出新链表的表头。 定义哑结点 头插法
     * 本题链接 ：https://www.nowcoder.com/practice/75e878df47f24fdc9dc3e400ec6058ca?tpId=13&tqId=11168&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
     */
    public ListNode ReverseList(ListNode head) {
        ListNode node = new ListNode(-1);
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = node.next;
            node.next = cur;
            cur = temp;
        }
        return node.next;
    }


    /**
     * 输入一个链表，输出该链表中倒数第k个结点。
     * 这是一道比较有水平的题
     * 解法一：暴力解。第一次遍历 先计算长度 第二次遍历找
     * 解法二：双指针。指针间间隔k。当尾指针指向null时，头指针就是要找的结点
     * 1 --> 2 --> 3 --> 4 --> 5
     * 输出倒数第二个
     * head 1
     * rear 3
     * 这道题如果用哑结点会显得很巧妙
     * 此处代码采用哑结点
     * 本题链接：https://www.nowcoder.com/practice/529d3ae5a407492994ad2a246518148a?tpId=13&tqId=11167&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
     * leetcode习题：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
     * leetcode上的题不用判断边界，简单一点。
     * cpp 版本
     * class Solution {
     * public:
     * ListNode* removeNthFromEnd(ListNode* head, int n) {
     * //一趟扫描的话考虑用栈。删除倒数第二个结点就是元素出栈出三个，改变第三个元素的指针 但是太消耗内存了。
     * //考虑其他方法 答案提示双指针法   1->2->3->4 删除倒数第二个结点3.
     * //尾指针最终指向NULL，头指针要指向2 2与NULL查了3个next
     * //同理 删除倒数第三个也是一样的。
     * ListNode* dummy = new ListNode(0);
     * dummy->next = head;
     * ListNode* first = dummy;//指向后面
     * ListNode* second = dummy;//指向前面
     * while(n>=0){
     * first = first->next;
     * n--;
     * }
     * while(first!=NULL){
     * first=first->next;
     * second = second->next;
     * }
     * second->next = second->next->next;
     * return dummy->next;
     * }
     * };
     */
    public ListNode FindKthToTail(ListNode head, int k) {
        ListNode node = new ListNode(-1);
        node.next = head;
        ListNode pre = node;
        ListNode rear = node;
        while (k != 0 && rear != null) {
            rear = rear.next;
            k--;
        }
        if (k >= 0 && rear == null) return null;
        while (rear != null) {
            pre = pre.next;
            rear = rear.next;
        }
        return pre;
    }

    /**
     * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
     * 单调不减 ： 递增？ 尾插法
     * 遍历即可 同样定义哑结点 方便操作
     */
    @SuppressWarnings("null")
    public ListNode Merge(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1);//定义一个哑结点方便操作
        ListNode cur = head;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                cur = cur.next;
                list1 = list1.next;
            } else {
                cur.next = list2;
                cur = cur.next;
                list2 = list2.next;
            }
        }
        if (list1 != null) cur.next = list1;
        if (list2 != null) cur.next = list2;
        return head.next;
    }

    /**
     * 输入两个链表，找出它们的第一个公共结点。
     * 双栈法
     * 暴力求解： 利用两个栈。每个链表的遍历结果存入栈内。
     * 然后栈内元素出栈。当出栈的元素相同，且下一个元素不同时，该节点就是第一个公共结点
     * <p>
     * 其他解法：先遍历得到它们的长度，长的先走多的几步，然后短的和他一起走。就可以找到第一个公共的。
     * 这种解法更优
     * 题目链接：https://www.nowcoder.com/practice/6ab1d9a29e88450685099d45c9e31e46?tpId=13&tqId=11189&tPage=2&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
     * *
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        Stack<ListNode> stack1 = new Stack<ListNode>();
        Stack<ListNode> stack2 = new Stack<ListNode>();
        while (pHead1 != null) {
            stack1.add(pHead1);
            pHead1 = pHead1.next;
        }
        while (pHead2 != null) {
            stack2.add(pHead2);
            pHead2 = pHead2.next;
        }
        ListNode pre = null;
        while (!stack1.empty() && !stack2.empty()) {
            ListNode peek = stack1.peek();
            //找到第一个公共结点 即栈中的最后一个公共结点
            if (stack1.pop().val == stack2.pop().val) pre = peek;
        }
        return pre;
    }

    /**
     * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留， 返回链表头指针。
     * 例如，链表1->2->3->3->4->4->5
     * 处理后为 1->2->5
     *
     * 	 * 三指针法 pre cur rear
     * 	 * 同时设置哑结点方便操作
     * 	 * 真菜 写了这么长的代码
     * 	本题链接：https://www.nowcoder.com/practice/fc533c45b73a41b0b44ccba763f866ef?tpId=13&tqId=11209&tPage=3&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
     *  leetcode习题：简化版https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
     *  *
     */
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) return pHead;
        ListNode ya = new ListNode(-1);
        ya.next = pHead;
        ListNode pre = ya;
        ListNode cur = pHead;
        ListNode rear = pHead.next;
        while (rear != null) {
            if (cur.val != rear.val) {
                pre = pre.next;
                cur = cur.next;
                rear = rear.next;
            } else {
                // 出现相等的点
                while (rear != null) {
                    if (rear.val == cur.val)
                        rear = rear.next;
                    else
                        break;
                }
                // 循环结束后找到了一个和pre不等的数
                if (rear == null)
                    pre.next = null;// pre的next是cur cur是重复结点不要了
                else {
                    pre.next = rear;
                    cur = rear;
                    rear = rear.next;
                }
            }
        }
        return ya.next;
    }
}


/**
 * 链表数据结构
 */
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}