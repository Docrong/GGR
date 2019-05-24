package com.boco.eoms.commons.statistic.base.test;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试使用
 * @author 李振友
 *
 */
public class ProduceDictionary {
	
	public static Map Dictionary = produceDictionary();
	
	/**
	 * 字典索引
	 * @return
	 */
	public static Map produceDictionary()
	{
		Map map = new HashMap();
		map.put("month", produceMonthDictionary());
		return map;
	}
	
	/**
	 * 12月字典
	 * @return
	 */
	public static Map produceMonthDictionary()
	{
		Map map = new HashMap();
		
		for(int i=1;i<=12;i++)
		{
			map.put("month" + i, String.valueOf(i));
		}
		
		return map;
	}
	
	public static void main(String[] args)
	{
		System.out.print(produceMonthDictionary());
	}
	
}
