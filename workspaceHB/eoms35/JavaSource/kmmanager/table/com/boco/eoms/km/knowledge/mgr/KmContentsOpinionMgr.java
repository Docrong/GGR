package com.boco.eoms.km.knowledge.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.knowledge.model.KmContentsOpinion;

/**
 * <p>
 * Title:知识管理
 * </p>
 * <p>
 * Description:知识管理
 * </p>
 * <p>
 * Tue Mar 24 10:33:01 CST 2009
 * </p>
 * 
 * @author eoms
 * @version 1.0
 * 
 */
 public interface KmContentsOpinionMgr {
 
	/**
	 *
	 * 取知识管理 列表
	 * @return 返回知识管理列表
	 */
	public List getKmContentsOpinions();
    
	/**
	 * 根据主键查询知识管理
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public KmContentsOpinion getKmContentsOpinion(final String id);
    
	/**
	 * 保存知识管理
	 * @param kmContentsOpinion 知识管理
	 */
	public void saveKmContentsOpinion(KmContentsOpinion kmContentsOpinion);
    
	/**
	 * 根据主键删除知识管理
	 * @param id 主键
	 */
	public void removeKmContentsOpinion(final String id);
	
	 /**
     * 根据知识id删除所用对应的知识评论记录
     * @param contentId
     */
    public void removeKmContentsOpinionList(final String contentId);
    
	/**
	 * 根据条件分页查询知识管理
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回知识管理的分页列表
	 */
	public Map getKmContentsOpinions(final Integer curPage, final Integer pageSize,
			final String whereStr);
			
}