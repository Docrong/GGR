package com.ggr.leecode.hash;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 示例 1:
 * <p>
 * 给定数组 nums = [1,1,2],
 * <p>
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 * <p>
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * <p>
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 */

public class 数组去重 {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 2, 3, 2};
        removeDuplicates(arr);
    }

    public static int removeDuplicates(int[] nums) {
        int a = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[a] != nums[i]) {
                nums[++a] = nums[i];

            }
        }
        System.out.println(nums);
        System.out.println(a + 1);
        for (int i : nums) {
            System.out.println("--" + i);
        }
        return a + 1;

    }
}
