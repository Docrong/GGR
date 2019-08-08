package com.ggr.leecode.string;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gr
 */
public class 无重复最长字符串 {

    public static void main(String[] args) {
        int result = lengthOfLongestSubstring("pwwkew");
        System.out.println("result:" + result);
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] chararr = s.toCharArray();
        List list = new ArrayList();

        int templength = 1, maxlength = 1;
        list.add(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (!list.contains(s.charAt(i))) {
                list.add(s.charAt(i));
                templength++;
                maxlength = Math.max(maxlength, templength);
            } else {
                int a = list.indexOf(s.charAt(i));
                list.add(s.charAt(i));
                System.out.println("输入前list:" + list);
                System.out.println("list:" + list + "*****a:" + a + "*****str:" + s.charAt(i));
                for (int j = 0; j <= a; j++) {
                    list.remove(0);//移除前面的元素
                }
                templength = list.size();
                System.out.println("输入后list:" + list);
            }
        }
        System.out.println(list);
        return maxlength;


    }
}
