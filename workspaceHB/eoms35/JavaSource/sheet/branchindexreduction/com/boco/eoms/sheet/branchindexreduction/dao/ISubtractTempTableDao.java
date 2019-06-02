package com.boco.eoms.sheet.branchindexreduction.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.sheet.branchindexreduction.model.SubtractTempTable;


/**
 * 
 * @author wangmingming
 *
 * 2017-8-4
 */
public interface ISubtractTempTableDao extends Dao {

    /**
     *
     * 保存核减内容表    
     * @param subtractTable 核减内容表
     * 
     */
    public void saveSubtractTempTable(SubtractTempTable subtractTable);
    
    
    /**
     * 根据id删除核减内容表
     * @param id 主键
     * 
     */
    public void removeSubtractTempTable(final String id);
     
	/**
	 * 根据条件查询
	 * @param condition
	 * @return
	 */
    public List getSubtractTempTablesByCondition(String condition);
    
    
    /**
     * 获取工单号重复的数据
     * @return
     */
	public List getRepeatedTables(final String serialKey);
	
	/**
	 * 获取工单号不存在的单据信息
	 * @return
	 */
	public List getInvalidTables(final String serialKey,final String sheetId);
	
	/**
	 * 获取未完成的数据信息
	 * @return
	 */
	public List getUnCompletedTables(final String serialKey);
	
	/**
	 * 获取遗漏的数据信息
	 * @return
	 */
	public List getOmitTables(final String serialKey,final String sheetId);
	
	/**
	 * 根据serialKey删除临时表数据
	 * @param serialKey
	 */
	public void delBySerialKey(final String serialKey);
	
	/**
	 * 重复取最后一条数据,其余删除
	 * @param serailKey
	 */
	public void delForRepeatTables(final String serailKey);
	
	/**
	 * 不符合条件的修改 是否成立为否
	 * @param serialKey
	 */
	public void updateForUncompletedTables(final String serialKey);
	
	/**
	 * 删除主表中不存在的数据
	 * @param serialKey
	 */
	public void delForInvalidTables(final String serialKey,final String sheetId);

	/**
	 * 根据临时表更新主表相关信息
	 * @param serialKey
	 */
	public void updateMainTable(String serialKey);

	/**
	 * 遗漏的工单信息处理
	 * @param serialKey
	 * @param sheetId
	 */
	public void updateForOmitTables(String serialKey, String sheetId);
}