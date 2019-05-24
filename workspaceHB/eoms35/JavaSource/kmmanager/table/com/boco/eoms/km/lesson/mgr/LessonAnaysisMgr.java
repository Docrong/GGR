package com.boco.eoms.km.lesson.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.lesson.model.LessonAnaysis;

/**
 * <p>
 * Title:考试课程分析
 * </p>
 * <p>
 * Description:考试课程分析
 * </p>
 * <p>
 * Tue Jul 07 09:44:42 CST 2009
 * </p>
 * 
 * @author mosquito
 * @version 1.0
 * 
 */
 public interface LessonAnaysisMgr {
 
	/**
	 *
	 * 取考试课程分析 列表
	 * @return 返回考试课程分析列表
	 */
	public List getLessonAnaysiss();
    
	/**
	 * 根据主键查询考试课程分析
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public LessonAnaysis getLessonAnaysis(final String id);
    
	/**
	 * 保存考试课程分析
	 * @param lessonAnaysis 考试课程分析
	 */
	public void saveLessonAnaysis(LessonAnaysis lessonAnaysis);
    
	/**
	 * 根据主键删除考试课程分析
	 * @param id 主键
	 */
	public void removeLessonAnaysis(final String id);
    
	/**
	 * 根据条件分页查询考试课程分析
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回考试课程分析的分页列表
	 */
	public Map getLessonAnaysiss(final Integer curPage, final Integer pageSize,
			final String whereStr);
			
}