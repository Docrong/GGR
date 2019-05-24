package com.boco.eoms.km.exam.mgr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.km.exam.model.KmExamQuestions;

/**
 * <p>
 * Title:题库管理
 * </p>
 * <p>
 * Description:题库管理
 * </p>
 * <p>
 * Fri May 08 16:40:11 CST 2009
 * </p>
 * 
 * @author hsl
 * @version 1.0
 * 
 */
 public interface KmExamQuestionsMgr {
 
	/**
	 *
	 * 取题库管理 列表
	 * @return 返回题库管理列表
	 */
	public List getKmExamQuestionss();
	
	/**
	 * 根据主键查询题库管理
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public KmExamQuestions getKmExamQuestions(final String id);
    
	/**
	 * 保存题库管理
	 * @param kmExamQuestions 题库管理
	 */
	public void saveKmExamQuestions(KmExamQuestions kmExamQuestions);
    
	/**
	 * 根据主键删除题库管理
	 * @param id 主键
	 */
	public void removeKmExamQuestions(final String id);
	
	/**
	 * 保存批量导入的试题和选项
	 * @param list
	 */
	public void saveKmExamQuestions(final Map map);
	
	/**
	 * 根据条件分页查询题库管理
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回题库管理的分页列表
	 */
	public Map getKmExamQuestionss(final Integer curPage, final Integer pageSize,
			final String whereStr);
			
}