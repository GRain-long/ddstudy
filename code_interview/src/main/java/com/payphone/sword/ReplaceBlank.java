package com.payphone.sword;

/**
 * @author payphone
 * @date 2020/6/21 19:12
 * @Description 替换字符串中的空格
 * <p>
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class ReplaceBlank {
    public static void main(String[] args) {
        System.out.println(new ReplaceBlank().replaceSpace(new StringBuffer("Java Eh WW")));
    }

    // 这题用cpp写比较地道
    // 时间复杂度O(n) 空间复杂度O(n)
    public String replaceSpace(StringBuffer str) {
        // 开辟一个新的数组
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                sb.append("%20");
            } else {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }


}
