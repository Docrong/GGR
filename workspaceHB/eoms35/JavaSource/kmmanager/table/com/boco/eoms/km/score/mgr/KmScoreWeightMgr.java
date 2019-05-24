package com.boco.eoms.km.score.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.score.model.KmScoreWeight;

/**
 * <p>
 * Title:积分权重
 * </p>
 * <p>
 * Description:积分权重表
 * </p>
 * <p>
 * Fri Aug 21 09:06:28 CST 2009
 * </p>
 * 
 * @author me
 * @version 1.0
 * 
 */
 public interface KmScoreWeightMgr {
 
	/**
	 *
	 * 取积分权重 列表
	 * @return 返回积分权重列表
	 */
	public List getKmScoreWeights();
    
	/**
	 * 根据主键查询积分权重
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public KmScoreWeight getKmScoreWeight(final String id);
    
	/**
	 * 保存积分权重
	 * @param kmScoreWeight 积分权重
	 */
	public void saveKmScoreWeight(KmScoreWeight kmScoreWeight);
    
	/**
	 * 根据主键删除积分权重
	 * @param id 主键
	 */
	public void removeKmScoreWeight(final String id);
    
	/**
	 * 根据条件分页查询积分权重
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回积分权重的分页列表
	 */
	public Map getKmScoreWeights(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
	public List getNextLevelKmScoreWeights(final String nodeId,final Integer nodeIdLength) ;
	
	public KmScoreWeight getKmScoreWeightByNodeId(final String nodeId) ;
	
	public void removeKmScoreWeightByNodeId(String nodeId) ;

}