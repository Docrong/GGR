package com.boco.eoms.km.exam.dao;

import com.boco.eoms.base.dao.Dao;
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
public interface KmExamAnswersDao extends Dao {


	/**
	 * 根据试卷id 题目id 参加考试人 查询答题信息
	 * @param testID
	 * @param questionID
	 * @param attendUser
	 * @return
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
    *取答题信息表列表
    * @return 返回答题信息表列表
    */
    public List getKmExamAnswerss();
    
    /**
    * 根据主键查询答题信息表
    * @param id 主键
    * @return 返回某id的答题信息表
    */
    public KmExamAnswers getKmExamAnswers(final String id);
    
    /**
    *
    * 保存答题信息表    
    * @param kmExamAnswers 答题信息表
    * 
    */
    public void saveKmExamAnswers(KmExamAnswers kmExamAnswers);
    
    /**
    * 根据id删除答题信息表
    * @param id 主键
    * 
    */
    public void removeKmExamAnswers(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getKmExamAnswerss(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
}