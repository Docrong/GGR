package com.ggr.leecode.backTracking;

import java.util.ArrayList;
import java.util.List;

public class 斐波那契数组拆分 {

    public static void main(String[] args) {
        splitIntoFibonacci("123456789");
    }

    public static List<Integer> splitIntoFibonacci(String S) {
        List<Integer> result = new ArrayList<Integer>();
        String str = S;
        if (str.startsWith("0")) {
            return result;
        }
        for (int i = 0; i < str.length(); i++) {

        }

        return result;

    }
}
