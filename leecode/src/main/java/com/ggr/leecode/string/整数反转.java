package com.ggr.leecode.string;

public class 整数反转 {

    public static void main(String[] args) {
        int x = 1534236469;
        reverse(x);
    }

    public static int reverse(int x) {
        if (x == 0)
            return 0;
        String str = "";
        Boolean zhengshu = true;
        if (x < 0) {
            zhengshu = false;
        }
        str = String.valueOf(x);
        String newStr = "";
        char[] arr = str.toCharArray();
        if (zhengshu) {
            for (int i = arr.length - 1; i >= 0; i--) {
                newStr += arr[i];
            }
        } else {
            for (int i = arr.length - 1; i >= 1; i--) {
                newStr += arr[i];
            }
            newStr = "-" + newStr;
        }
        newStr = newStr.startsWith("0") ? newStr.substring(1, newStr.length()) : newStr;
        //越界直接捕获异常
        try {
            x = Integer.parseInt(newStr);
        } catch (Exception e) {
            return 0;
        }

        System.out.println(x);
        return x;
    }
}
