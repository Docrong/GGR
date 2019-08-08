package com.ggr.leecode.string;

public class 实现strStr功能 {

    public static void main(String[] args) {

    }

    public static int strStr(String haystack, String needle) {
        if (needle.equals("")) {
            return 0;
        }
        int loc = haystack.indexOf(needle);
        return loc;
    }
}
