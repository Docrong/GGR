package com.ggr.leecode.hash;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c
 * + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：
 * <p>
 * 答案中不可以包含重复的四元组。
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * <p>
 * 满足要求的四元组集合为： [ [-1, 0, 0, 1], [-2, -1, 1, 2], [-2, 0, 0, 2] ]
 */
public class 四数之和 {

    public static void main(String[] args) {

        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        List result = fourSum(nums, target);
        System.out.println(result);
    }

    public static List fourSum(int[] nums, int target) {
		/*List result = new ArrayList();
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 3; i++) {
			for (int index1 = i + 1; index1 < nums.length - 2; index1++) {
				int index2 = index1 + 1;
				int index3 = nums.length - 1;

				while (index2 < index3) {
					if (nums[i] + nums[index1] + nums[index2] + nums[index3] == target) {
						result.add(Arrays.asList(nums[i], nums[index1], nums[index2], nums[index3]));
						while (index2 < index3 && nums[index2] == nums[index2 + 1]) {
							index2++;
							index3--;
						}
						while (index2 < index3 && nums[index3] == nums[index3 - 1]) {
							index2++;
							index3--;
						}
						index2++;
						index3--;
					} else if (nums[i] + nums[index1] + nums[index2] + nums[index3] < target) {
						index2++;
					} else {
						index3--;
					}
				}
			}

		}
		return result;
*/
        Arrays.sort(nums);
        List<List<Integer>> ls = new ArrayList<List<Integer>>();
        for (int i = 0; i < nums.length - 3; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                for (int j = i + 1; j < nums.length - 2; j++) {
                    if (j == i + 1 || (j > i + 1 && nums[j] != nums[j - 1])) {
                        int l = j + 1, r = nums.length - 1, sum = target - nums[j] - nums[i];
                        while (l < r) {
                            if (nums[l] + nums[r] == sum) {
                                ls.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                                while (l < r && nums[l] == nums[l + 1]) {
                                    l++;
                                }
                                while (l < r && nums[r] == nums[r - 1]) {
                                    r--;
                                }
                                l++;
                                r--;
                            } else if (nums[l] + nums[r] < sum) {
                                while (l < r && nums[l] == nums[l + 1]) {
                                    l++;
                                }
                                l++;
                            } else {
                                while (l < r && nums[r] == nums[r - 1]) {
                                    r--;
                                }
                                r--;
                            }
                        }
                    }
                }
            }
        }
        return ls;
    }
}
