package com.boco.eoms.repository.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.repository.model.TawLocalRepositoryUp;

/**
 * <p>
 * Title:tawLocalRepositoryUp
 * </p>
 * <p>
 * Description:tawLocalRepositoryUp
 * </p>
 * <p>
 * Fri Oct 30 16:52:13 CST 2009
 * </p>
 * 
 * @author 李锋
 * @version 1.0
 * 
 */
 public interface TawLocalRepositoryUpMgr {
 
	/**
	 *
	 * 取tawLocalRepositoryUp 列表
	 * @return 返回tawLocalRepositoryUp列表
	 */
	public List getTawLocalRepositoryUps();
    
	/**
	 * 根据主键查询tawLocalRepositoryUp
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public TawLocalRepositoryUp getTawLocalRepositoryUp(final String id);
    
	/**
	 * 保存tawLocalRepositoryUp
	 * @param tawLocalRepositoryUp tawLocalRepositoryUp
	 */
	public void saveTawLocalRepositoryUp(TawLocalRepositoryUp tawLocalRepositoryUp);
    
	/**
	 * 根据主键删除tawLocalRepositoryUp
	 * @param id 主键
	 */
	public void removeTawLocalRepositoryUp(final String id);
    
	/**
	 * 根据条件分页查询tawLocalRepositoryUp
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回tawLocalRepositoryUp的分页列表
	 */
	public Map getTawLocalRepositoryUps(final Integer curPage, final Integer pageSize,
			final String whereStr);
			
}