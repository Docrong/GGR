package com.boco.eoms.km.expert.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.expert.model.KmExpertExp;

/**
 * <p>
 * Title:工作经验
 * </p>
 * <p>
 * Description:工作经验
 * </p>
 * <p>
 * Mon Jun 15 18:07:23 CST 2009
 * </p>
 * 
 * @author zhangxb
 * @version 1.0
 * 
 */
 public interface KmExpertExpMgr {
 
	/**
	 *
	 * 取工作经验 列表
	 * @return 返回工作经验列表
	 */
	public List getKmExpertExps();
    
	/**
	 * 根据主键查询工作经验
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public KmExpertExp getKmExpertExp(final String id);
    
	/**
	 * 保存工作经验
	 * @param kmExpertExp 工作经验
	 */
	public void saveKmExpertExp(KmExpertExp kmExpertExp);
    
	/**
	 * 根据主键删除工作经验
	 * @param id 主键
	 */
	public void removeKmExpertExp(final String id);
    
    /**
     * 根据id批量删除工作经验
     * @param id 主键
     * add by lijun @2009-06-20
     */    
    public void removeKmExpertExps(final String ids[]) ;	
	/**
	 * 根据条件分页查询工作经验
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回工作经验的分页列表
	 */
	public Map getKmExpertExps(final Integer curPage, final Integer pageSize,
			final String whereStr);

	public Map getKmExpertExpsByUserId(final Integer curPage, final Integer pageSize,
			final String userId);
}