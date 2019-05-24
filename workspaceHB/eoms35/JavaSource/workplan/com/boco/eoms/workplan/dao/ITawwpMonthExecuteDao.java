package com.boco.eoms.workplan.dao;

import java.util.List;

import com.boco.eoms.workplan.model.TawwpMonthExecute;
import com.boco.eoms.workplan.model.TawwpMonthPlan;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 12:29:02 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public interface ITawwpMonthExecuteDao {
	/**
	 * 保存月度作业计划执行内容
	 * 
	 * @param _tawwpMonthExecute
	 *            TawwpMonthExecute 月度作业计划执行内容类
	 * @throws Exception
	 *             操作异常
	 */
	public void saveMonthExecute(TawwpMonthExecute _tawwpMonthExecute);

	/**
	 * 删除月度作业计划执行内容
	 * 
	 * @param _tawwpMonthExecute
	 *            TawwpMonthExecute 月度作业计划执行内容类
	 * @throws Exception
	 *             操作异常
	 */
	public void deleteMonthExecute(TawwpMonthExecute _tawwpMonthExecute);

	/**
	 * 修改月度作业计划执行内容
	 * 
	 * @param _tawwpMonthExecute
	 *            TawwpMonthExecute 月度作业计划执行内容类
	 * @throws Exception
	 *             操作异常
	 */
	public void updateMonthExecute(TawwpMonthExecute _tawwpMonthExecute);

	/**
	 * 查询月度作业计划执行内容信息
	 * 
	 * @param id
	 *            String 月度作业计划执行内容标识
	 * @throws Exception
	 *             操作异常
	 * @return TawwpMonthExecute 月度作业计划执行内容类
	 */
	public TawwpMonthExecute loadMonthExecute(String id);

	/**
	 * 查询所有月度作业计划执行内容信息
	 * 
	 * @throws Exception
	 *             操作异常
	 * @return List 月度作业计划执行内容类列表
	 */
	public List listMonthExecute();

	/**
	 * 查询所有月度作业计划执行内容信息
	 * 
	 * @throws Exception
	 *             操作异常
	 * @return List 月度作业计划执行内容类列表
	 */
	public List listMonthExecuteCommand();

	/**
	 * 查询属于指定月度作业计划的所有执行内容信息
	 * 
	 * @param _tawwpMonthPlan
	 *            TawwpMonthPlan 月度作业计划
	 * @throws Exception
	 *             操作异常
	 * @return List 月度作业计划执行内容类列表
	 */
	public List listMonthExecute(TawwpMonthPlan _tawwpMonthPlan);

	public void updateMonthExecute(String executer, String executerType, String monthExecuteIds);

	public List getMonthExecute(String ids);
	/**
	 * 根据planId获取执行项
	 * @param planIds 
	 * @return
	 */
	
	public List getMonthExecuteByPlanIds(String planIds);
	
	public List getMonthExecutebyMonthPlanId(String monthplanid);
	/**
	 * 根据年计划id获取一个时间段中的执行列表
	 * 
	 * @param _tawwpYearPlanId 年度作业计划id
	 * @throws Exception
	 *             异常
	 * @return List 一个时间段中的执行内容
	 */
	public List getGxExecuteMonthtList(String _tawwpYearPlanId);
	
	
	
}
