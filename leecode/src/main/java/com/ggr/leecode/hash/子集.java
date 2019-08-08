package com.ggr.leecode.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,2] 输出: [ [2], [1], [1,2,2], [2,2], [1,2], [] ]
 *
 * @author gr
 */

/**
 * @author gr
 */
public class 子集 {

    private String id;
    private String name;
    private String age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    /**
     * @param arg
     */
    /**
     * @param arg
     */
    public static void main(String arg[]) {
//		int[]nums=new int[] {1,2,3};
//		List result=subsetsWithDup(nums);
//		System.out.println(nums);
//		Integer []intarr=new Integer[] {1,2,3};
//		List intlist=Arrays.asList(intarr);

        子集 a = new 子集();
        a.setAge("1");
        a.setId("2");
        a.setName("3");
        Object obj = a;
        子集 a2 = (子集) obj;
        System.out.println(a2.id);
        System.out.println(a2.name);

    }

    public static List subsetsWithDup(int[] nums) {
        Set set = new HashSet();
        List list = new ArrayList();
        if (nums != null)
            set.add(new ArrayList());
        Arrays.sort(nums);
        for (int i : nums)
            list.add(i);
        for (int i = 0; i < list.size(); i++) {

        }
        System.out.println(set);
        return list;
    }

}
