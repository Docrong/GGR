package com.boco.eoms.km.statics.mgr;

import java.util.Map;

/**
 * <p>
 * Title:知识库统计
 * </p>
 * <p>
 * Description:知识库统计
 * </p>
 * <p>
 * Mon Mar 30 14:39:15 CST 2009
 * </p>
 * 
 * @author ljt
 * @version 0.1
 * 
 */
 public interface BaseStatisticMgr {
 
	/**
	 * 根据条件分页查询知识库统计
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @return 返回知识库统计的分页列表
	 */
	public Map getBaseStatistics(final Integer curPage, final Integer pageSize);
			
}