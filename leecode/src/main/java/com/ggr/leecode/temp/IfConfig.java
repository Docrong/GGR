package com.ggr.leecode.temp;

public class IfConfig {
    public static void main(String[] args) {
        String mainNetSortOne = "101010415";
        String operateType = "5";
        int mainExamineNum = 1;
        if (mainNetSortOne.equals("101010415") && "5".equals(operateType)) {
            if (mainExamineNum == 3) {
                System.out.println("最多延期三次");
            }//最多延期三次
        } else if (!mainNetSortOne.equals("101010415") && mainExamineNum == 1 && "5".equals(operateType)) {
            System.out.println("最多延期一次");
        }

        System.out.println("结束");
    }
}
