package com.boco.eoms.km.knowledge.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.knowledge.model.KmContentsApply;

/**
 * <p>
 * Title:知识申请
 * </p>
 * <p>
 * Description:知识申请
 * </p>
 * <p>
 * Tue Jul 14 10:27:17 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
 public interface KmContentsApplyMgr {
 
	/**
	 *
	 * 取知识申请 列表
	 * @return 返回知识申请列表
	 */
	public List getKmContentsApplys();
    
	/**
	 * 根据主键查询知识申请
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public KmContentsApply getKmContentsApply(final String id);
    
	/**
	 * 保存知识申请
	 * @param kmContentsApply 知识申请
	 */
	public void saveKmContentsApply(KmContentsApply kmContentsApply);
    
	/**
	 * 根据主键删除知识申请
	 * @param id 主键
	 */
	public void removeKmContentsApply(final String id);
    
	/**
	 * 根据条件分页查询知识申请
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回知识申请的分页列表
	 */
	public Map getKmContentsApplys(final Integer curPage, final Integer pageSize,
			final String whereStr, final String orderStr);
	
	/**
	 * 根据条件分页查询知识申请
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回知识申请的分页列表
	 */
	public Map getKmContentsApplys(final Integer curPage, final Integer pageSize,
			final String applyTheme,final String startDate,final String endDate, final String orderStr);
	
	/**
	 * 根据条件查询知识申请排行
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @param userId 用户ID
	 * @param deptId 部门ID
	 * @param themeId 知识类型ID
	 * @return 返回知识申请的排行列表
	 */
	public Map getKmContentsApplyRanks(final String startDate, final String endDate,
			final String userId, final String deptId, final String themeId ,final String maxSize);
			
}