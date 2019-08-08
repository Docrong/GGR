package com.ggr.leecode.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 * @author gr
 */
public class 数组取众数 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 6, 6, 6, 6,};
        int result = majorityElement(nums);
        System.out.println(result);
    }


    public static int majorityElement(int[] nums) {
/*		Arrays.sort(nums);
		return nums[nums.length/2];
*/
        //解法2
        int count = 1;
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (result == nums[i])
                count++;
            else {
                count--;
                if (count == 0) {
                    result = nums[i];
                }
            }
        }
        return result;
    }

}
