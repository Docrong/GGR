package com.boco.eoms.workplan.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.workplan.model.TawwpMonthExecuteUser;
import com.boco.eoms.workplan.model.TawwpMonthPlan;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 12:37:31 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public interface ITawwpMonthExecuteUserDao {
	/**
	   * 保存月度作业计划执行内容执行人
	   * @param _tawwpMonthExecuteUser TawwpMonthExecuteUser 月度作业计划执行内容执行人类
	   * @throws Exception 操作异常
	   */
	  public void saveMonthExecuteUser(TawwpMonthExecuteUser _tawwpMonthExecuteUser) ;
	  /**
	   * 删除月度作业计划执行内容执行人
	   * @param _tawwpMonthExecuteUser TawwpMonthExecuteUser 月度作业计划执行内容执行人类
	   * @throws Exception 操作异常
	   */
	  public void deleteMonthExecuteUser(TawwpMonthExecuteUser
	                                     _tawwpMonthExecuteUser);
	  /**
	   * 删除月度作业计划执行内容执行人
	   * @param monthPlanId 月计划ID
	   */
	  public void deleteMonthExecuteUser(TawwpMonthPlan _monthPlan);
	  /**
	   * 修改月度作业计划执行内容执行人
	   * @param _tawwpMonthExecuteUser TawwpMonthExecuteUser 月度作业计划执行内容执行人类
	   * @throws Exception 操作异常
	   */
	  public void updateMonthExecuteUser(TawwpMonthExecuteUser
	                                     _tawwpMonthExecuteUser);

	  /**
	   * 查询月度作业计划执行内容执行人信息
	   * @param id String 月度作业计划执行内容执行人标识
	   * @throws Exception 操作异常
	   * @return TawwpMonthExecuteUser 月度作业计划执行内容执行人类
	   */
	  public TawwpMonthExecuteUser loadMonthExecuteUser(String id) ;

	  /**
	   * 查询所有月度作业计划执行内容执行人信息
	   * @throws Exception 操作异常
	   * @return List 月度作业计划执行内容执行人类列表
	   */
	  public List listMonthExecuteUser();

	  /**
	   * modified by lijia 2005-11-28
	   * 查询当前用户(或用户所在部门、岗位)需要执行(包括执行完成但未归档)的日常执行作业计划
	   * @param _userId String 用户名
	   * @param _deptId String 部门编号
	   * @param _orgPostStr String 岗位编号字符串
	   * @throws Exception 操作异常
	   * @return List 执行作业计划执行内容(个人)类列表
	   */
	  public List listExecuteByuser(String userid,String deptid,String orgPostStr);
	  
	  public List listMonthExecuteUser(String _userId, String _deptId,
	                                   String _orgPostStr) ;
	  /** 
	   * 查询当前用户(或用户所在部门、岗位)需要执行(包括执行完成但未归档)的日常执行作业计划
	   * @param _userId String 用户名
	   * @param _deptId String 部门编号
	   * @param _orgPostStr String 岗位编号字符串
	   * @throws Exception 操作异常
	   * @return List 执行作业计划执行内容(个人)类列表
	   */
	  public List listMonthExecute(String _userId, String _deptId,
	                                   String _orgPostStr);
	  /**
	   * 查询1、当前月度2、当前用户所属机房,需要执行(包括执行完成但未归档)的值班执行作业计划
	   * @param _roomId String 执行机房编号
	   * @param _yearFlag String 执行年度
	   * @param _monthFlag String 执行月度
	   * @throws Exception 操作异常
	   * @return List 执行作业计划执行内容(个人)类列表
	   */
	  public List listMonthExecuteUserDuty(String _roomId, String _yearFlag,
	                                       String _monthFlag);

	  /**
	   * 批量修改执行状态
	   * @param _monthPlan String 月度作业计划编号
	   * @param _state String 状态
	   * @throws Exception 异常
	   */
	  public void updateState(TawwpMonthPlan _monthPlan, String _state);
	  
	  public List getMonthExecuteUser(String monthplanid); 
	  public List getMonthExecuteUserAll(String monthplanid,int flag); 
	  
	  public List getMonthExecuteStat(final String monthplanid, String startDate,
				String endDate);

		public int getWorkAll(final String monthplanid, String condi);

		public int getWorkCompleteAll(final String condition,
				final String monthplanid, final String condi);

		public int getWorkOnTimeAll(final String condition,
				final String monthplanid, final String condi);
		public List listMonthExecuteUser(String _userId, String _deptId, String _roomId,
				String _year, String _month);

		public List getMonthExecuteUser(String monthplanid, String state);

		public List listMonthByExecuteUser(String _userId, String _deptId,
				String _orgPostStr, String _year, String _month);


}
