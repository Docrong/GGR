package com.boco.eoms.km.expert.mgr;

import java.util.List;
import com.boco.eoms.km.expert.model.KmExpertScore;

/**
 * <p>
 * Title:知识库专家问答
 * </p>
 * <p>
 * Description:知识库专家问答
 * </p>
 * <p>
 * 2009-07-27
 * </p>
 * 
 * @author daizhigang
 * @version 1.0
 * 
 */

public interface KmExpertScoreMgr {
	

	/**
	 * 保存当天专家积分
	 * 
	 * @param kmExpertScore 积分变化参数
	 * 
	 */
	public void upOrSaveDayScore(KmExpertScore kmExpertScore);

	/**
	 * 保存主动输入积分
	 * 
	 * @param kmExpertScoreSum 专家Score对象
	 * 
	 */
	public void saveExpertScore(KmExpertScore kmExpertScoreSum);

	/**
	 * 保存当天专家积分
	 * 
	 * @param expertUserId[] 专家id集合
	 * @param inputScore[] 主动输入积分集合
	 * 
	 */
	public void saveInputScore(String expertUserId[],String baseScore[],String inputScore[]);

	/**
	 * 取所有专家积分
	 * 
	 * @return 返回所有专家积分
	 */
	public List getKmExpertScores();
	
	
	/**
	 * 按专业和打分状态取所有专家积分
	 * 
	 * @return 按专业和打分状态返回所有专家积分
	 */
	public List getKmExpertScores(String specialty,String treeType);

	/**
	 * 根据主键查询专家积分记录
	 * 
	 * @param id 主键
	 * 
	 * @return 返回某id的专家积分记录
	 */
	public KmExpertScore getKmExpertScore(final String id);

	/**
	 * 保存专家积分记录
	 * 
	 * @param kmExpertScore 专家积分记录
	 */
	public void saveKmExpertScore(KmExpertScore kmExpertScore);

	/**
	 * 根据专家积分，对专家进行排行
	 * 
	 * @param 
	 */
	public List getExpertScoresOrder(String user_id,int num);

	/**
	 * 
	 * 根据主键删除专家积分记录
	 * 
	 * @param id 主键
	 * 
	 */
	public void removeKmExpertScore(final String id);
	
	/**
	 * 
	 * 根据专家id查询记录类型是sum的总积分
	 * 
	 * @param expertUserId 专家ID
	 * 
	 */
	public int getSumByexpertUserId(final String expertUserId);
}