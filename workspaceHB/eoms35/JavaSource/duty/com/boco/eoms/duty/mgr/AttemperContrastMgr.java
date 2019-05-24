package com.boco.eoms.duty.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.duty.model.AttemperContrast;
import com.boco.eoms.duty.webapp.form.AttemperContrastForm;

/**
 * <p>
 * Title:网调对比表
 * </p>
 * <p>
 * Description:网调对比表
 * </p>
 * <p>
 * Thu Apr 02 14:11:03 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version 3.5
 * 
 */
 public interface AttemperContrastMgr {
 
	/**
	 *
	 * 取网调对比表 列表
	 * @return 返回网调对比表列表
	 */
	public List getAttemperContrasts();
    
	/**
	 * 根据主键查询网调对比表
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public AttemperContrast getAttemperContrast(final String id);
    
	/**
	 * 保存网调对比表
	 * @param attemperContrast 网调对比表
	 */
	public void saveAttemperContrast(AttemperContrast attemperContrast);
    
	/**
	 * 根据主键删除网调对比表
	 * @param id 主键
	 */
	public void removeAttemperContrast(final String id);
    
	/**
	 * 根据条件分页查询网调对比表
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回网调对比表的分页列表
	 */
	public Map getAttemperContrasts(final Integer curPage, final Integer pageSize,
			final String whereStr) throws Exception;
	
	/**
	 * 根据Form中数据，获取查询条件
	 * @param AttemperContrastForm
	 * @return 返回 condition,字符串
	 */
	public String getSearchCondition(AttemperContrastForm attemperContrastForm);
	
			
}