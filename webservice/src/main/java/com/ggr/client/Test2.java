package com.ggr.client;

import java.util.Hashtable;

public class Test2 {


    public static void main(String[] args) {
        double[] price = {529, 164.5, 334.5 + 30};
        double[] price2 = {506, 164.5, 311.5};
        //����Ь���23��Ӱ�������
        float bill = (float) ((529 - 60) * 0.75);
        System.out.println(bill);

        System.out.println(311.5 - 247);

        Hashtable table = new Hashtable<>();
        table.put("1", "value1");
        System.out.println(table);
    }
}
