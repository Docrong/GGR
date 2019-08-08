package com.ggr.leecode.string;

import java.util.ArrayList;
import java.util.List;

public class 买卖股票最好时机 {

    public static void main(String[] args) {


        int[] prices = new int[]{1, 5, 3, 7, 2};
        maxProfit(prices);
    }

    public static int maxProfit(int[] prices) {

        int profit = 0;
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }

        }
        return profit;
    }
}
