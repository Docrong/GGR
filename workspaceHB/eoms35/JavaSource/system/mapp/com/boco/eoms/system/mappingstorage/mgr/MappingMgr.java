package com.boco.eoms.system.mappingstorage.mgr;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.boco.eoms.system.mappingstorage.model.Mapping;

/**
 * <p>
 * Title:存储映射
 * </p>
 * <p>
 * Description:存储映射
 * </p>
 * <p>
 * Wed Apr 08 09:10:47 CST 2009
 * </p>
 * 
 * @author sam
 * @version 1.0
 * 
 */
 public interface MappingMgr {
 
	/**
	 *
	 * 取存储映射 列表
	 * @return 返回存储映射列表
	 */
	public List getMappings();
    
	/**
	 * 根据主键查询存储映射
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public Mapping getMapping(final String id);
	
	public void updateMapping(Mapping mapping);
    
	/**
	 * 保存存储映射
	 * @param mapping 存储映射
	 */
	public void saveMapping(Mapping mapping);
    
	/**
	 * 根据主键删除存储映射
	 * @param id 主键
	 */
	public void removeMapping(final String id);
    
	/**
	 * 根据条件分页查询存储映射
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回存储映射的分页列表
	 */
	public Map getMappings(final Integer curPage, final Integer pageSize,
			final String whereStr);
	//动态生成表
	public void genTable(String tableName)throws SQLException;
	
	//接受appcode和数据
	
	public String insertValue(String appcode, 
			String sheetkey,String rootId,String dict) throws SQLException, Exception;
	
	//通过beanid反向显示

	public String dictIdToName(String appcode,String sheetkey);
	
	public String checkUnique(String tableName);
	
			
}