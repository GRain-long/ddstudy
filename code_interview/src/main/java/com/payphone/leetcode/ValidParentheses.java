package com.payphone.leetcode;

import java.util.HashMap;
import java.util.Stack;

/**
 * leetcode 第20题，判断有效括号
 */
public class ValidParentheses {
    public static void main(String[] args) {
        ValidParentheses obj = new ValidParentheses();
        String str = "()[}]";

        System.out.println(obj.isValid(str));
    }

    /**
     * 用stack map
     * 是左括号就入栈
     * 是右括号就判断栈顶元素和它是否匹配
     * 栈中元素为空，不匹配。
     * 栈中元素括号与xx不对应，不匹配
     * 其他情况均为匹配
     *
     * @param str
     * @return
     */
    public boolean isValid(String str) {
        if (str == null || str.equals("")) return true;
        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        for (int i = 0; i < str.length(); i++) {
            char curChar = str.charAt(i);
            // 左括号入栈
            if (curChar == '(' || curChar == '[' || curChar == '{') {
                stack.push(curChar);
            } else {
                // 右括号对比
                if (stack.empty()) return false;
                Character pop = stack.pop();
                // 括号不匹配 false
                if (!map.get(curChar).equals(pop)) {
                    return false;
                }
            }
        }
        if (!stack.empty()) return false;
        return true;
    }

    /**
     * 只用stack 不用map。比较那块写麻烦点。
     * 开了个map 也没用多少内存
     */
    public boolean isValid2(String str) {
        if (str == null || str.equals("")) return true;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char cur = str.charAt(i);
            // 左括号 入栈
            if (cur == '(' || cur == '[' || cur == '{') {
                stack.push(cur);
            } else {
                // 右括号 对比
                if (cur == ']' && stack.pop() != '[') {
                    return false;
                } else if (cur == ')' && stack.pop() != '(') {
                    return false;
                } else if (cur == '}' && stack.pop() != '{') {
                    return false;
                }
            }
        }
        if (!stack.empty()) return false;
        return true;
    }
}
