package com.work.ggr.leecode.hash;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class Arr去重 {

	public static void main(String[] args) {
		int []arr=new int[] {1,1,2,3,2};
		removeDuplicates(arr);
	}
		
	public static int removeDuplicates(int[] nums) {
		 int a=0;
			for(int i=1;i<nums.length;i++) {
				if(nums[a]!=nums[i]) {
					nums[++a]=nums[i];
					
				}
			}
			System.out.println(nums);
			System.out.println(a+1);
			return a+1;
	        
    }	
}
