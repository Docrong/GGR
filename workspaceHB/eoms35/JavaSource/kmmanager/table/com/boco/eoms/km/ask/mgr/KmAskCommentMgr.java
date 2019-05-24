package com.boco.eoms.km.ask.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.ask.model.KmAskComment;

/**
 * <p>
 * Title:问答评论
 * </p>
 * <p>
 * Description:问答评论
 * </p>
 * <p>
 * Fri Aug 14 15:49:40 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
 public interface KmAskCommentMgr {
 
	/**
	 *
	 * 取问答评论 列表
	 * @return 返回问答评论列表
	 */
	public List getKmAskComments();
    
	/**
	 * 查询某问题下的所有评论信息
	 * @param questionId 问答id
	 * @return 所有评论集合
	 */
	public List getKmAskComments(final String questionId);
	
	/**
	 * 根据主键查询问答评论
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public KmAskComment getKmAskComment(final String id);
    
	/**
	 * 保存问答评论
	 * @param kmAskComment 问答评论
	 */
	public void saveKmAskComment(KmAskComment kmAskComment);
    
	/**
	 * 根据主键删除问答评论
	 * @param id 主键
	 */
	public void removeKmAskComment(final String id);
    
	/**
	 * 根据条件分页查询问答评论
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回问答评论的分页列表
	 */
	public Map getKmAskComments(final Integer curPage, final Integer pageSize,
			final String whereStr);
			
}