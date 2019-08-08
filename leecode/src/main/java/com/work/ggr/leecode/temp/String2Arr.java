package com.work.ggr.leecode.temp;

import java.util.ArrayList;
import java.util.List;

public class String2Arr {
    public static void main(String[] args) {

        String str = "1;";
        String[] arr = str.split(";");
        System.out.println(arr[1]);
        System.out.println("结束");
    }

}
