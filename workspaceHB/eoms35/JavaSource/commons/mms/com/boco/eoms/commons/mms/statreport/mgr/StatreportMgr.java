package com.boco.eoms.commons.mms.statreport.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.commons.mms.statreport.model.Statreport;

/**
 * <p>
 * Title:报表实例
 * </p>
 * <p>
 * Description:彩信报系统
 * </p>
 * <p>
 * Wed Feb 18 11:35:28 CST 2009
 * </p>
 * 
 * @author 李振友
 * @version 3.5
 * 
 */
 public interface StatreportMgr {
 
	/**
	 *
	 * 取报表实例 列表
	 * @return 返回报表实例列表
	 */
	public List getStatreports();
    
	/**
	 * 根据主键查询报表实例
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public Statreport getStatreport(final String id);
	
	/**
	 * 根据彩信报id获取报表
	 * @param id
	 * @return
	 */
	public List  getStatreportForMmsreportId(final String mmsreportId);
    
	/**
	 * 保存报表实例
	 * @param statreport 报表实例
	 */
	public void saveStatreport(Statreport statreport);
    
	/**
	 * 根据主键删除报表实例
	 * @param id 主键
	 */
	public void removeStatreport(final String id);
    
	/**
	 * 根据条件分页查询报表实例
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回报表实例的分页列表
	 */
	public Map getStatreports(final Integer curPage, final Integer pageSize,
			final String whereStr);
			
}