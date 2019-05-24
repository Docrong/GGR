package com.boco.eoms.km.ask.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.ask.model.KmAskReply;

/**
 * <p>
 * Title:答题
 * </p>
 * <p>
 * Description:回答答案
 * </p>
 * <p>
 * Tue Aug 04 15:52:08 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
 public interface KmAskReplyMgr {
 
	/**
	 *
	 * 取答题 列表
	 * @return 返回答题列表
	 */
	public List getKmAskReplys();
    
	/**
	 * 根据问题查询所有回答
	 * @param questionId
	 * @return
	 */
	public List getKmAskReplysByQuestionId(final String questionId);
	
	/**
	 * 根据主键查询答题
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public KmAskReply getKmAskReply(final String id);
    
	/**
	 * 保存答题
	 * @param kmAskReply 答题
	 */
	public void saveKmAskReply(KmAskReply kmAskReply);
    
	/**
	 * 根据主键删除答题
	 * @param id 主键
	 */
	public void removeKmAskReply(final String id);
    
	/**
	 * 根据条件分页查询答题
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回答题的分页列表
	 */
	public Map getKmAskReplys(final Integer curPage, final Integer pageSize,
			final String whereStr);
			
}