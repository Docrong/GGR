package com.boco.eoms.km.score.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.score.model.KmScoreSet;

/**
 * <p>
 * Title:积分设定表
 * </p>
 * <p>
 * Description:积分设定表
 * </p>
 * <p>
 * Fri Aug 07 14:32:13 CST 2009
 * </p>
 * 
 * @author me
 * @version 1.0
 * 
 */
 public interface KmScoreSetMgr {
 
	/**
	 *
	 * 取积分设定表 列表
	 * @return 返回积分设定表列表
	 */
	public List getKmScoreSets();
    
	/**
	 * 根据主键查询积分设定表
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public KmScoreSet getKmScoreSet(final String id);
    
	/**
	 * 保存积分设定表
	 * @param kmScoreSet 积分设定表
	 */
	public void saveKmScoreSet(KmScoreSet kmScoreSet);
    
	/**
	 * 根据主键删除积分设定表
	 * @param id 主键
	 */
	public void removeKmScoreSet(final String id);
    
	/**
	 * 根据条件分页查询积分设定表
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回积分设定表的分页列表
	 */
	public Map getKmScoreSets(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
    public void removeKmScoreSetByPowerName(final String powerName) ;
    
    public void removeKmScoreSetId(final String id);
}