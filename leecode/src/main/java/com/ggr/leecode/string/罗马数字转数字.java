package com.ggr.leecode.string;

import java.util.HashMap;
import java.util.Map;

public class 罗马数字转数字 {

    public static void main(String[] args) {
        romanToInt("IV");
    }

    public static int romanToInt(String s) {
        int result = 0;
        Map<String, Integer> romanMap = new HashMap<String, Integer>();
        romanMap.put("I", 1);
        romanMap.put("V", 5);
        romanMap.put("X", 10);
        romanMap.put("L", 50);
        romanMap.put("C", 100);
        romanMap.put("D", 500);
        romanMap.put("M", 1000);


        String[] arrs = s.split("");
        int max = 0;
        for (String str : arrs) {
            int index = romanMap.get(str);
            if (index > max) {
                result += max;
            } else {

            }
        }
        System.out.println(result);
        return result;
    }
}
