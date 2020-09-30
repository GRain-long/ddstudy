package com.payphone.leetcode.old;

public class StringQuestion {
    /**
     * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。
     * 同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。 例如，“student. a am
     * I”。后来才意识到，这家伙原来把句子单词的顺序翻转了， 正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，
     * 你能帮助他么？
     *  * 本题链接：https://www.nowcoder.com/practice/3194a4f4cf814f63919d0790578d51f3?tpId=13&tqId=11197&tPage=3&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
     *  * 给个水题：翻转字符串 如abcdefg  输出gfedcba
     *  * 给个难题：https://pintia.cn/problem-sets/994805260223102976/problems/994805323154440192 这是PAT乙级中相当有难度的题
     *
     *
     * Java暴力解题
     *   将字符串分割为字符串数组。
     *   再将数组中的元素按条件进行拼接
     *   无技术含量不推荐。
     *   推荐下面的C++解法
     * */
    public String ReverseSentence(String str) {
        //容易忽略的点是 null和"         "
        if(str == null || str.trim().equals("")){
            return str;
        }
        String result = "";
        String[] arrayStr = str.split(" ");
        // "I am a student.";
        int length = arrayStr.length;
        for (int i = 0; i < length; i++) {
            if (i == length - 1) {
                result += arrayStr[length - i - 1];
            } else {
                result += arrayStr[length - i - 1] + " ";
            }
        }
        return result;
    }

}
