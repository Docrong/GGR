package com.work.ggr.leecode.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 
 * 满足要求的三元组集合为： [ [-1, 0, 1], [-1, -1, 2] ]
 * 
 * @author gr
 *
 */

public class 三数之和 {

	public static void main(String[] args) {
		int[] nums=new int[] {-1, 0, 1, 2, -1, -4};
		List list=threeSum(nums);
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
		List result=new ArrayList(set);*/
		List result=new ArrayList();
		Arrays.sort(nums);
		for(int i=0;i<nums.length-2;i++) {
			if(i==0||(i>0&&nums[i]!=nums[i-1])) {
				
			}
		}
		return result;
	}
}
