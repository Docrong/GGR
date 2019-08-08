package com.ggr.leecode.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为： [ [-1, 0, 1], [-1, -1, 2] ]
 *
 * @author gr
 * 更改为双指针的方式获取结果之和
 */

public class 三数之和 {

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List list = threeSum(nums);
        System.out.println(list);
        System.out.println(list.size());
    }

    @SuppressWarnings("unchecked")
    public static List<List<Integer>> threeSum(int[] nums) {
		/*
		 * 超时可太致命了
		Set set=new HashSet();
		for(int i=0;i<nums.length;i++) {
			for(int j=i+1;j<nums.length;j++) {
				for(int k=j+1;k<nums.length;k++) {
					if(nums[i]+nums[j]+nums[k]==0) {
						int[]arr=new int[] {nums[i],nums[j],nums[k]};
						Arrays.sort(arr);
						set.add(new ArrayList(Arrays.asList(arr[0],arr[1],arr[2])));						
					}
				}
			}
		}
		List result=new ArrayList(set);
		return result;
		*/
        List result = new ArrayList();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (nums[i] != nums[i - 1])) {
                int index1 = i + 1;
                int index2 = nums.length - 1;
                while (index1 < index2) {
                    if (nums[i] + nums[index1] + nums[index2] == 0) {
                        result.add(Arrays.asList(nums[i], nums[index1], nums[index2]));
                        while (index1 < index2 && nums[index1] == nums[index1 + 1]) {
                            index1++;
                        }
                        while (index2 > index1 && nums[index2] == nums[index2 - 1]) {
                            index2--;
                        }
                        index1++;
                        index2--;
                    } else if (nums[i] + nums[index1] + nums[index2] < 0) {
                        index1++;
                    } else {
                        index2--;
                    }
                }
            }
        }
        return result;
    }
}
