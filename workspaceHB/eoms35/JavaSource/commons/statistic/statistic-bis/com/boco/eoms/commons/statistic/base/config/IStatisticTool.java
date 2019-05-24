package com.boco.eoms.commons.statistic.base.config;

import java.util.List;

/**
 * 使用统计工具需要实现的接口
 * @author lizhenyou
 *
 */
public interface IStatisticTool {
	
	/**
	 * 统计使用的字典
	 * @param id 字典id
	 * @return 字典唯一标识[String]List 
	 */
	public List getDictionaryList(String id);
}
