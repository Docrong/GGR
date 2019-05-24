package com.boco.eoms.sheet.daiweiindexreduction.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.sheet.daiweiindexreduction.model.DaiSubtractTable;



/**
 * 
 * @author wangmingming
 *
 * 2017-8-4
 */
public interface IDaiSubtractTableMgr {

    /**
     *
     *取核减内容列表
     * @return 返回核减内容列表
     */
    public List getDaiSubtractTables();
    
   
    /**
     * 根据主键查询核减内容表
     * @param id 主键
     * @return 返回某id的核减内容表（对象）
     */
    public DaiSubtractTable getDaiSubtractTable(final String id);
    
    /**
     *
     * 保存核减内容表    
     * @param subtractTable 核减内容表
     * 
     */
    public void saveDaiSubtractTable(DaiSubtractTable daisubtractTable);
    
    /**
     * 根据id删除核减内容表
     * @param id 主键
     * 
     */
    public void removeDaiSubtractTable(final String id);
    /**
     * 根据id批量删除核减内容表
     * @param ids 主键
     * 
     */
    public void removeDaiSubtractTable(final String[] ids);
    
    /**
     * 分页取列表
     * @param curPage 当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数,result(list) curPage页的记录
     */
    public Map getDaiSubtractTables(final Integer curPage, final Integer pageSize,
			final String whereStr);
    
	
	/**
	*   根据条件查询核减内容表
	*/
    public List getDaiSubtractTablesByCondition(String condition);
	
}