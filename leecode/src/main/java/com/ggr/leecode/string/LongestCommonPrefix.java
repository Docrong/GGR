package com.ggr.leecode.string;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * <p>
 * 所有输入只包含小写字母 a-z 。
 *
 * @author gr
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] strs = new String[]{"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(strs));
        ;
    }

    static public String longestCommonPrefix(String[] strs) {
        StringBuffer sb = new StringBuffer();
        if (strs.length == 1)
            return strs[0];
        else if (strs.length > 1) {
            if (strs[0].equals("")) {
                return "";
            }
            int len = strs[0].length();
            for (int i = 0; i < strs[0].length(); i++) {
                char curr = strs[0].charAt(i);
                for (int j = 0; j < strs.length; j++) {
                    if (strs[j].length() <= i || strs[j].charAt(i) != curr) {
                        return sb.toString();
                    }
                    if (strs[j].charAt(i) == curr && j == strs.length - 1) {
                        sb.append(curr);
                    }
                }
            }
        }

        return sb.toString();

    }
}
