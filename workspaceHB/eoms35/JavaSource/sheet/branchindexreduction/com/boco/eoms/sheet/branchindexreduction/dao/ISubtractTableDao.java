package com.boco.eoms.sheet.branchindexreduction.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.sheet.branchindexreduction.model.SubtractTable;


/**
 * 
 * @author wangmingming
 *
 * 2017-8-4
 */
public interface ISubtractTableDao extends Dao {

    /**
     *
     *取核减内容列表
     * @return 返回核减内容列表
     */
    public List getSubtractTables();
    
    /**
     * 根据主键查询核减内容表
     * @param id 主键
     * @return 返回某id的核减内容表
     */
    public SubtractTable getSubtractTable(final String id);
    
    /**
     *
     * 保存核减内容表    
     * @param subtractTable 核减内容表
     * 
     */
    public void saveSubtractTable(SubtractTable subtractTable);
    
    /**
     * 根据id删除核减内容表
     * @param id 主键
     * 
     */
    public void removeSubtractTable(final String id);
     
    /**
     * 分页取列表
     * @param curPage 当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数,result(list) curPage页的记录
     */
    public Map getSubtractTables(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
    public List getSubtractTablesByCondition(String condition);
}