package com.boco.eoms.km.statics.mgr;

import java.util.Map;

/**
 * <p> Title:知识审核情况统计 </p>
 * <p> Description:知识审核情况统计 </p>
 * <p> Mon Mar 30 14:39:15 CST 2009 </p>
 * @author zhangxiaobo
 * @version 0.1
 * 
 */

public interface PersonalAuditStatisticMgr {
	/**
	 * 知识审核情况统计（部门）
	 * 
	 * @param curPage 当前页
	 * @param pageSize 每页显示条数
	 * @param startDate 统计开始时间
	 * @param endDate 统计结束时间
	 * @return map中total为条数,result(list) curPage页的记录
	 */
	public Map getPersonalAuditStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate);

	/**
	 * 知识审核情况统计（部门）
	 * 
	 * @param curPage 当前页
	 * @param pageSize 每页显示条数
	 * @param startDate 统计开始时间
	 * @param endDate 统计结束时间
	 * @return map中total为条数,result(list) curPage页的记录
	 */
	public Map getDeptAuditStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate);;

}