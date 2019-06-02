package com.boco.eoms.km.exam.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.exam.model.KmExamAnswers;

/**
 * <p>
 * Title:答题信息表
 * </p>
 * <p>
 * Description:答题信息表
 * </p>
 * <p>
 * Mon May 11 11:00:31 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
 public interface KmExamAnswersMgr {
 
	/**
	* 根据试卷id 题目id 参加考试人 查询答题信息
	* @param testID
	* @param questionID
	* @param attendUser
	*  @return
	*/
	public KmExamAnswers getKmExamAnswers(final String testID,final String questionID,final String attendUser);

	/**
	* 对某个人的某套试卷进行求和
	* @param testID
	* @param attendUser
	* @return该人该套试卷的总成绩
	*/
	public String getSumScore(String testID,String attendUser);
	 
	/**
	 *
	 * 取答题信息表 列表
	 * @return 返回答题信息表列表
	 */
	public List getKmExamAnswerss();
    
	/**
	 * 根据主键查询答题信息表
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public KmExamAnswers getKmExamAnswers(final String id);
    
	/**
	 * 保存答题信息表
	 * @param kmExamAnswers 答题信息表
	 */
	public void saveKmExamAnswers(KmExamAnswers kmExamAnswers);
    
	/**
	 * 根据主键删除答题信息表
	 * @param id 主键
	 */
	public void removeKmExamAnswers(final String id);
    
	/**
	 * 根据条件分页查询答题信息表
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回答题信息表的分页列表
	 */
	public Map getKmExamAnswerss(final Integer curPage, final Integer pageSize,
			final String whereStr);
			
}