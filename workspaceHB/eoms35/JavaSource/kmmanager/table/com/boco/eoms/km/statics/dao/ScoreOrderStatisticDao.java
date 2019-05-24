package com.boco.eoms.km.statics.dao;

import java.util.Date;
import java.util.List;
import com.boco.eoms.base.dao.Dao;

public interface ScoreOrderStatisticDao extends Dao {

	/**
	 * 知识排名（使用数排行）- Informix 数据库
	 * @param num 排名显示数量
	 * @param startDate 统计开始时间
	 * @param endDate 统计结束时间
	 * @return 知识排名（使用数排行）
	 */
    public List getKnowledgeUsedOrder(final int nunber, final Date startDate, final Date endDate);
    
	/**
	 * 知识排名（使用数排行）- Oracle 数据库
	 * @param num 排名显示数量
	 * @param startDate 统计开始时间
	 * @param endDate 统计结束时间
	 * @return 知识排名（使用数排行）
	 */
    public List getKnowledgeUsedOrder(final int nunber, final String startDate, final String endDate);
	/**
	 * 员工知识贡献排名（新建数排行）- Informix 数据库
	 * @param num 排名显示数量
	 * @param startDate 统计开始时间
	 * @param endDate 统计结束时间
	 * @return 员工知识贡献排名（新建数排行）
	 */
    public List getMonthUserScoreOrder(final int nunber, final Date startDate, final Date endDate);
    
	/**
	 * 员工知识贡献排名（新建数排行）- Oracle 数据库
	 * @param num 排名显示数量
	 * @param startDate 统计开始时间
	 * @param endDate 统计结束时间
	 * @return 员工知识贡献排名（新建数排行）
	 */
    public List getMonthUserScoreOrder(final int nunber, final String startDate, final String endDate);

	public List getUserScoreOrder(int number);

	/**
	 * 当月知识库知识总量排名- Informix 数据库
	 * @param num 排名显示数量
	 * @param startDate 统计开始时间
	 * @param endDate 统计结束时间
	 * @return 当月知识库知识总量排名
	 */
    public List getMonthKnowledgeCountOrder(final int nunber, final Date startDate, final Date endDate);
    public List getKnowledgeCountOrder(final int nunber);
    public List getKnowledgeReadOrder(final int nunber);
	/**
	 * 当月知识库知识总量排名- Oracle 数据库
	 * @param num 排名显示数量
	 * @param startDate 统计开始时间
	 * @param endDate 统计结束时间
	 * @return 当月知识库知识总量排名
	 */
    public List getMonthKnowledgeCountOrder(final int nunber, final String startDate, final String endDate);
	/**
	 * 知识排名（使用数排行）
	 * @param num 排名显示数量
	 * @return 知识排名（使用数排行）
	 */
    public List getKnowledgeUsedOrder(final int nunber);

	public List getUserKnowledScoreOrder(int number);

	public List getUserKnowledScoreOByUserId(final String userId);
}
