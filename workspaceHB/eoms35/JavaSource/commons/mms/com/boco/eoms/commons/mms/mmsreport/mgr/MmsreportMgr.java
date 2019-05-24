package com.boco.eoms.commons.mms.mmsreport.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.commons.mms.mmsreport.model.Mmsreport;

/**
 * <p>
 * Title:彩信报实例
 * </p>
 * <p>
 * Description:彩信报系统
 * </p>
 * <p>
 * Wed Feb 18 18:16:20 CST 2009
 * </p>
 * 
 * @author 李振友
 * @version 3.5
 * 
 */
 public interface MmsreportMgr {
 
	/**
	 *
	 * 取彩信报实例 列表
	 * @return 返回彩信报实例列表
	 */
	public List getMmsreports();
    
	/**
	 * 根据主键查询彩信报实例
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public Mmsreport getMmsreport(final String id);
    
	/**
	 * 保存彩信报实例
	 * @param mmsreport 彩信报实例
	 */
	public void saveMmsreport(Mmsreport mmsreport);
    
	/**
	 * 根据主键删除彩信报实例
	 * @param id 主键
	 */
	public void removeMmsreport(final String id);
    
	/**
	 * 根据条件分页查询彩信报实例
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回彩信报实例的分页列表
	 */
	public Map getMmsreports(final Integer curPage, final Integer pageSize,
			final String whereStr);
			
}