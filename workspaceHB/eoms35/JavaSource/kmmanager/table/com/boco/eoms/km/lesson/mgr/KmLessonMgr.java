package com.boco.eoms.km.lesson.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.lesson.model.KmLesson;

/**
 * <p>
 * Title:课程创建
 * </p>
 * <p>
 * Description:课程创建
 * </p>
 * <p>
 * Wed Jul 01 15:12:51 CST 2009
 * </p>
 * 
 * @author mosquito
 * @version 1.0
 * 
 */
 public interface KmLessonMgr {
 
	/**
	 *
	 * 取课程创建 列表
	 * @return 返回课程创建列表
	 */
	public List getKmLessons();
	/**
	 * 根据主键查询课程创建
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public KmLesson getKmLesson(final String id);
    
	/**
	 * 保存课程创建
	 * @param creatlesson 课程创建
	 */
	public void saveKmLesson(KmLesson creatlesson);

	/**
	 * 根据主键删除课程创建
	 * @param id 主键
	 */
	public void removeKmLesson(final String id);
    
	/**
	 * 根据条件分页查询课程创建
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回课程创建的分页列表
	 */
	public Map getKmLessons(final Integer curPage, final Integer pageSize,
			final String whereStr);
			
}