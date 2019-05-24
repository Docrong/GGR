package com.boco.eoms.workplan.dao;

import java.util.List;

import com.boco.eoms.workplan.model.TawwpYearCheck;

public interface ITawwpYearCheckDao {
	/**
	   * 保存年度作业计划审批信息
	   * @param _tawwpYearCheck TawwpYearCheck 年度作业计划审批信息篿
	   * @  操作异常
	   */
	  public void saveYearCheck(TawwpYearCheck _tawwpYearCheck) ;
	  /**
	   * 删除年度作业计划审批信息
	   * @param _tawwpYearCheck TawwpYearCheck 年度作业计划审批信息篿
	   * @  操作异常
	   */
	  public void deleteYearCheck(TawwpYearCheck _tawwpYearCheck) ;

	  /**
	   * 修改年度作业计划审批信息
	   * @param _tawwpYearCheck TawwpYearCheck 年度作业计划审批信息篿
	   * @  操作异常
	   */
	  public void updateYearCheck(TawwpYearCheck _tawwpYearCheck);

	  /**
	   * 查询年度作业计划审批信息
	   * @param id String 年度作业计划审批信息标识
	   * @  操作异常
	   * @return TawwpYearCheck 年度作业计划审批信息篿
	   */
	  public TawwpYearCheck loadYearCheck(String id);

	  /**
	   * 查询所有年度作业计划审批信忿
	   * @  操作异常
	   * @return List 年度作业计划审批信息类列蟿
	   */
	  public List listYearCheck()  ;

	  /**
	   * 查询需要当前用户进行审批的年度作业计划,相应的审批信忿
	   * @param _userId String
	   * @ 
	   * @return List
	   */
	  public List listYearCheck(String _userId);
	  /**
	   * 查询所有未审批年度作业计划审批信息
	   * @  操作异常
	   * @return List 未审批年度作业计划审批信息类列表
	   */
	  public List listUnPassYearCheck();
}
