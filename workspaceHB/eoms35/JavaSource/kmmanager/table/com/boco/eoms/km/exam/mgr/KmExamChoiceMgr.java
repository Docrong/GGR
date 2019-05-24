package com.boco.eoms.km.exam.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.exam.model.KmExamChoice;

/**
 * <p>
 * Title:选项表
 * </p>
 * <p>
 * Description:选项表
 * </p>
 * <p>
 * Fri May 08 16:40:12 CST 2009
 * </p>
 * 
 * @author hsl
 * @version 1.0
 * 
 */
 public interface KmExamChoiceMgr {
 
	/**
	* 查询某试题下的所用选项
	* @param questionID
	* @return
	*/
	public List getKmExamChoices(final String questionID);
	 
	/**
	 *
	 * 取选项表 列表
	 * @return 返回选项表列表
	 */
	public List getKmExamChoices();
    
	/**
	 * 根据主键查询选项表
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public KmExamChoice getKmExamChoice(final String id);
    
	/**
	 * 保存选项表
	 * @param kmExamChoice 选项表
	 */
	public void saveKmExamChoice(KmExamChoice kmExamChoice);
    
	/**
	 * 根据主键删除选项表
	 * @param id 主键
	 */
	public void removeKmExamChoice(final String id);
    
	/**
	 * 根据条件分页查询选项表
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回选项表的分页列表
	 */
	public Map getKmExamChoices(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
	public List getKmExamChoicesByQuestionID(final String questionID);
	
 }