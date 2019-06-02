package com.boco.eoms.km.table.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.table.model.KmTableDict;

/**
 * <p>
 * Title:知识字段字典
 * </p>
 * <p>
 * Description:知识字段字典
 * </p>
 * <p>
 * Mon Mar 02 14:55:43 CST 2009
 * </p>
 * 
 * @author 吕卫华
 * @version 1.0
 * 
 */
 public interface KmTableDictMgr {
 
	/**
	 *
	 * 取知识字段字典 列表
	 * @return 返回知识字段字典列表
	 */
	public List getKmTableDicts();
    
	/**
	 * 根据主键查询知识字段字典
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public KmTableDict getKmTableDict(final String id);
    
	/**
	 * 保存知识字段字典
	 * @param kmTableDict 知识字段字典
	 */
	public void saveKmTableDict(KmTableDict kmTableDict);
    
	/**
	 * 根据主键删除知识字段字典
	 * @param id 主键
	 */
	public void removeKmTableDict(final String id);
    
	/**
	 * 根据条件分页查询知识字段字典
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回知识字段字典的分页列表
	 */
	public Map getKmTableDicts(final Integer curPage, final Integer pageSize,
			final String whereStr);
			
}