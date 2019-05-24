package com.boco.eoms.km.statics.mgr;

import java.util.List;

/**
 * <p>
 * Title:知识积分排行
 * </p>
 * <p>
 * Description:知识积分排行
 * </p>
 * @author daizhigang
 * @version 0.1
 * 
 */
 public interface ScoreOrderStatisticMgr {
		/**
		 * 知识排名（使用数排行）
		 * @param num 排名显示数量
		 * @param startDate 统计开始时间
		 * @param endDate 统计结束时间
		 * @return 知识排名（使用数排行）
		 */
	 public List getKnowledgeUsedOrder(final int num);
		/**
		 * 知识排名（使用数排行）
		 * @param num 排名显示数量
		 * @param startDate 统计开始时间
		 * @param endDate 统计结束时间
		 * @return 知识排名（使用数排行）
		 */
	 public List getKnowledgeUsedOrder(final int num,final String startDate,final String endDate);
		/**
		 * 员工知识贡献排名（新建数排行）
		 * @param num 排名显示数量
		 * @param startDate 统计开始时间
		 * @param endDate 统计结束时间
		 * @return 员工知识贡献排名（新建数排行）
		 */
	 public List getMonthUserScoreOrder(final int num,final String startDate,final String endDate);
	 public List getUserScoreOrder(final int num);
		/**
		 * 当月知识库知识总量排名
		 * @param num 排名显示数量
		 * @param startDate 统计开始时间
		 * @param endDate 统计结束时间
		 * @return 当月知识库知识总量排名
		 */
	 public List getMonthKnowledgeCountOrder(final int num,final String startDate,final String endDate);
	 public List getKnowledgeCountOrder(int num);
	public List getUserKnowledScoreOrder(int orderNumber);
	
	public List getUserKnowledScoreOByUserId(final String userId);
	public List getKnowledgeReadOrder(int num);
	 
}
