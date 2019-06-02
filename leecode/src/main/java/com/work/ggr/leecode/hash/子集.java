package com.work.ggr.leecode.hash;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 
 * 说明：解集不能包含重复的子集。
 * 
 * 示例:
 * 
 * 输入: [1,2,2] 输出: [ [2], [1], [1,2,2], [2,2], [1,2], [] ]
 * 
 * @author gr
 *
 */
/**
 * @author gr
 *
 */
public class 子集 {

	/**
	 * @param arg
	 */
	/**
	 * @param arg
	 */
	public static void main(String arg[]) {
		int[]nums=new int[] {1,2,3};
		List result=subsetsWithDup(nums);
		System.out.println(result);
	}

	public static List subsetsWithDup(int[] nums) {
		List result=new ArrayList();
		Set set=new HashSet();
		if(nums!=null)
			set.add(new ArrayList());
		set.add(nums);
		System.out.println(set);
		return new ArrayList(set);
	}

}
