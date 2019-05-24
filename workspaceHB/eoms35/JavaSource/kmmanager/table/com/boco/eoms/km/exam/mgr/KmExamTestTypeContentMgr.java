package com.boco.eoms.km.exam.mgr;

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
 public interface KmExamTestTypeContentMgr {
 
	/**
	 *
	 * 取题型内容表 列表
	 * @return 返回题型内容表列表
	 */
	public List getKmExamTestTypeContents();
    
	/**
	 * 根据主键查询题型内容表
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public KmExamTestTypeContent getKmExamTestTypeContent(final String id);
    
	/**
	 * 查询某类型下所有题目信息
	 * @param testTypeID
	 * @return
	 */
	public List getKmExamTestTypeContentByTestTypeID(final String testTypeID);
	
	/**
	 * 保存题型内容表
	 * @param kmExamTestTypeContent 题型内容表
	 */
	public void saveKmExamTestTypeContent(KmExamTestTypeContent kmExamTestTypeContent);
    
	/**
	 * 根据主键删除题型内容表
	 * @param id 主键
	 */
	public void removeKmExamTestTypeContent(final String id);
    
	/**
	 * 根据条件分页查询题型内容表
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回题型内容表的分页列表
	 */
	public Map getKmExamTestTypeContents(final Integer curPage, final Integer pageSize,
			final String whereStr);
			
}