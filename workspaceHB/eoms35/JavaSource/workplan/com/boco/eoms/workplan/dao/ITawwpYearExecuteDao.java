package com.boco.eoms.workplan.dao;

import java.util.List;

import com.boco.eoms.workplan.model.TawwpYearExecute;

public interface ITawwpYearExecuteDao {
	/**
	   * 保存年度作业计划执行内容
	   * @param _tawwpYearExecute TawwpYearExecute 年度作业计划执行内容类
	   * @throws Exception 操作异常
	   */
	  public void saveYearExecute(TawwpYearExecute _tawwpYearExecute);
	  /**
	   * 删除年度作业计划执行内容
	   * @param _tawwpYearExecute TawwpYearExecute 年度作业计划执行内容类
	   * @throws Exception 操作异常
	   */
	  public void deleteYearExecute(TawwpYearExecute _tawwpYearExecute) ;

	  /**
	   * 修改年度作业计划执行内容
	   * @param _tawwpYearExecute TawwpYearExecute 年度作业计划执行内容类
	   * @throws Exception 操作异常
	   */
	  public void updateYearExecute(TawwpYearExecute _tawwpYearExecute);

	  /**
	   * 查询年度作业计划执行内容信息
	   * @param id String 年度作业计划执行内容标识
	   * @throws Exception 操作异常
	   * @return TawwpYearExecute 年度作业计划执行内容类
	   */
	  public TawwpYearExecute loadYearExecute(String id);
	   /**
	   * 查询所有年度作业计划执行内容信息
	   * @throws Exception 操作异常
	   * @return List 年度作业计划执行内容类列表
	   */
	  public List ListYearExecute() ;
	
}
