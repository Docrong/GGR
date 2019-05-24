package com.boco.eoms.km.exam.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.km.exam.model.KmExamTestTypeContent;

/**
 * <p>
 * Title:题型内容表
 * </p>
 * <p>
 * Description:题型内容表
 * </p>
 * <p>
 * Fri May 08 16:40:12 CST 2009
 * </p>
 * 
 * @author hsl
 * @version 1.0
 * 
 */
public interface KmExamTestTypeContentDao extends Dao {

    /**
    *
    *取题型内容表列表
    * @return 返回题型内容表列表
    */
    public List getKmExamTestTypeContents();
    
    /**
	 * 查询某类型下所有题目信息
	 * @param testTypeID
	 * @return
	 */
	public List getKmExamTestTypeContentByTestTypeID(final String testTypeID);
    
    /**
    * 根据主键查询题型内容表
    * @param id 主键
    * @return 返回某id的题型内容表
    */
    public KmExamTestTypeContent getKmExamTestTypeContent(final String id);
    
    /**
    *
    * 保存题型内容表    
    * @param kmExamTestTypeContent 题型内容表
    * 
    */
    public void saveKmExamTestTypeContent(KmExamTestTypeContent kmExamTestTypeContent);
    
    /**
    * 根据id删除题型内容表
    * @param id 主键
    * 
    */
    public void removeKmExamTestTypeContent(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getKmExamTestTypeContents(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
}