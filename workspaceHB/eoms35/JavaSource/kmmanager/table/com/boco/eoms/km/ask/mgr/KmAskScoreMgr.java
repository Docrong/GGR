package com.boco.eoms.km.ask.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.ask.model.KmAskScore;

/**
 * <p>
 * Title:问答积分配置
 * </p>
 * <p>
 * Description:问答积分配置
 * </p>
 * <p>
 * Wed Aug 19 16:41:06 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
 public interface KmAskScoreMgr {
 
	/**
	 *
	 * 取问答积分配置 列表
	 * @return 返回问答积分配置列表
	 */
	public List getKmAskScores();
    
	 /**
	 * 根据操作id 查询积分配置
	 * @param operateId
	 * @return
	 */
	public KmAskScore getKmAskScoreByOperateId(final Integer operateId);
	
	/**
	 * 根据主键查询问答积分配置
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public KmAskScore getKmAskScore(final String id);
    
	/**
	 * 保存问答积分配置
	 * @param kmAskScore 问答积分配置
	 */
	public void saveKmAskScore(KmAskScore kmAskScore);
    
	/**
	 * 根据主键删除问答积分配置
	 * @param id 主键
	 */
	public void removeKmAskScore(final String id);
    
	/**
	 * 根据条件分页查询问答积分配置
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回问答积分配置的分页列表
	 */
	public Map getKmAskScores(final Integer curPage, final Integer pageSize,
			final String whereStr);
			
}