package com.ggr.leecode.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target
 * 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * <p>
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 *
 * @author gr
 */
public class 最近三数之和 {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 2, 1, -4};
        int target = 1;
        int result = threeSumClosest(nums, target);
        System.out.println(result);
        System.out.println("-------------------------");

    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int l = i + 1;
                int r = nums.length - 1;
                while (l < r) {
                    if (nums[i] + nums[l] + nums[r] == target) {
                        return nums[i] + nums[l] + nums[r];

                    }
                    sum = Math.abs(nums[i] + nums[l] + nums[r] - target) < Math.abs(sum - target) ? nums[i] + nums[l] + nums[r] : sum;
                    if (nums[i] + nums[l] + nums[r] < target) {
                        while (l < r && nums[l] == nums[l + 1]) {
                            l++;
                        }
//						System.out.println(":"+(nums[i]+nums[l]+nums[r]));
                        l++;
                    } else {
                        while (l < r && nums[r] == nums[r - 1]) {
                            r--;
                        }
//						System.out.println(":"+(nums[i]+nums[l]+nums[r]));
                        r--;
                    }
                }
            }
        }

        return sum;
		
		 /*if (nums == null || nums.length < 3) {
	            throw new IllegalArgumentException("argument is error.");
	        }
	        Arrays.sort(nums);
	        int sum = nums[0] + nums[1] + nums[2];
	        for (int i = 0; i < nums.length - 2; i++) {
	            if (i == 0 || nums[i] != nums[i - 1]) {
	                int l = i + 1;
	                int r = nums.length - 1;
	                while (l < r) {
	                    int temp = nums[i] + nums[l] + nums[r];//三个数之和
	                    if (temp == target) {
	                        return temp;
	                    }
	                    sum = Math.abs(target - temp) < Math.abs(target - sum) ? temp : sum;//返回绝对值比较小的一个
	                    if (temp < target) {
	                        while (l < r && nums[l] == nums[l + 1]) {
	                            l++;
	                        }
	                        l++;
	                    } else {
	                        while (r > l && nums[r] == nums[r - 1]) {
	                            r--;
	                        }
	                        r--;
	                    }
	                }
	            }
	        }
	        return sum;*/
    }
}
