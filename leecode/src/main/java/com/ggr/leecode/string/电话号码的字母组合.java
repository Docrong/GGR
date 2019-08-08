package com.ggr.leecode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @apiNote 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class 电话号码的字母组合 extends Object {

    public static void main(String[] args) {

        List result = letterCombinations("23");
        System.out.println(result);

    }

    public static List<String> letterCombinations(String digits) {
        List list = null;
        Map<String, String> charmap = new HashMap<String, String>();
        charmap.put("1", "");
        charmap.put("2", "abc");
        charmap.put("3", "def");
        charmap.put("4", "ghi");
        charmap.put("5", "jkl");
        charmap.put("6", "mno");
        charmap.put("7", "pqrs");
        charmap.put("8", "tuv");
        charmap.put("9", "wxyz");


        char[] strarr = digits.toCharArray();
        List<String> strlist = new ArrayList<String>();
        for (char aStrarr : strarr) {
            String str = String.valueOf(aStrarr);
            strlist.add(charmap.get(str));
        }
        System.out.println(strlist);
        for (int i = 0; i < strlist.size(); i++) {

        }

        return strlist;
    }
}
