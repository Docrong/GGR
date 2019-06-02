package com.boco.eoms.km.statics.mgr;

import java.util.Map;

/**
 * <p>
 * Title:知识使用人本期（周、月、季、年）使用知识情况统计表
 * </p>
 * <p>
 * Description:知识使用人本期（周、月、季、年）使用知识情况统计表
 * </p>
 * <p>
 * Mon Mar 30 14:39:15 CST 2009
 * </p>
 * 
 * @author ljt
 * @version 0.1
 * 
 */
 public interface PersonalUseStatisticMgr {
 
	/**
	 * 根据条件分页查询知识使用人本期（周、月、季、年）使用知识情况统计表
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param period 查询范围(year,month,season,week)
	 * @return 返回知识使用人本期（周、月、季、年）使用知识情况统计表的分页列表
	 */
	public Map getPersonalUseStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate);
	
	public Map getDeptUseStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate);
}