package com.boco.eoms.sheet.branchindexreduction.service;

import java.util.List;

import com.boco.eoms.sheet.branchindexreduction.model.SubtractTempTable;


/**
 * 
 * @author wangmingming
 *
 * 2017-8-4
 */
public interface ISubtractTempTableMgr {

    /**
     *
     *取核减内容列表
     * @return 返回核减内容列表
     */
    public List getSubtractTempTablesByCondition(String condition);
    
    public void saveSubtractTable(SubtractTempTable substractTempTable);
    
    public void saveSubtractTableBatch(List substractTempTable);
    
    /**
     * 根据id删除核减内容表
     * @param id 主键
     * 
     */
    public void removeSubtractTable(final String id);
    /**
     * 根据id批量删除核减内容表
     * @param ids 主键
     * 
     */
    public void removeSubtractTable(final String[] ids);
    
    /**
     * 获取工单号重复的数据
     * @return
     */
	public List getRepeatedTables(final String serialKey);
	
	/**
	 * 获取工单号不存在的单据信息
	 * @return
	 */
	public List getInvalidTables(final String serialKey,String sheetId);
	
	/**
	 * 获取未完成的数据信息
	 * @return
	 */
	public List getUnCompletedTables(final String serialKey);
	
	/**
	 * 获取遗漏的数据信息 遗漏:主表中有，excel表格中没有
	 * @return
	 */
	public List getOmitTables(final String serialKey,String sheetId);

	public void removeBySerialKey(final String serialKey);
	
	/**
	 * 用户选择继续之后的操作
	 * @param serialKey
	 */
	public void actionForContinue(final String serialKey,final String sheetId);
	
	/**
	 * 正常数据执行保存操作
	 * @param serialKey
	 */
	void actionForCOmmon(String serialKey);
	
	/**
	 * 获取说明字段为空的数据
	 * @param string
	 * @return
	 */
	public List getEmptyTables(String string);
	

}