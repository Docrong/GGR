package com.boco.eoms.commonfaulthj.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.commonfaulthj.model.Commonfaulthj;

/**
 * <p>
 * Title:commonfaulthj
 * </p>
 * <p>
 * Description:commonfaulthj
 * </p>
 * <p>
 * Thu Dec 18 15:28:05 CST 2014
 * </p>
 * 
 * @author zhoupan
 * @version 3.5
 * 
 */
 public interface CommonfaulthjMgr {
 
	/**
	 *
	 * 取commonfaulthj 列表
	 * @return 返回commonfaulthj列表
	 */
	public List getCommonfaulthjs();
    
	/**
	 * 根据主键查询commonfaulthj
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public Commonfaulthj getCommonfaulthj(final String id);
    
	/**
	 * 保存commonfaulthj
	 * @param commonfaulthj commonfaulthj
	 */
	public void saveCommonfaulthj(Commonfaulthj commonfaulthj);
    
	/**
	 * 根据主键删除commonfaulthj
	 * @param id 主键
	 */
	public void removeCommonfaulthj(final String id);
    
	/**
	 * 根据条件分页查询commonfaulthj
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回commonfaulthj的分页列表
	 */
	public Map getCommonfaulthjs(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
	public Map getMapList(final Integer curPage, final Integer pageSize,
			final String whereStr);
			
}