package com.boco.eoms.workplan.dao;

import java.util.List;

import com.boco.eoms.workplan.model.TawwpExecuteReport;
import com.boco.eoms.workplan.model.TawwpMonthPlan;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 25, 2008 11:20:37 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public interface ITawwpExecuteReportDao {

	/**
	 * 保存执行作业计划周、月报
	 * 
	 * @param _tawwpExecuteReport
	 *            TawwpExecuteReport 执行作业计划周、月报类
	 * @throws Exception
	 *             操作异常
	 */
	public void saveExecuteReport(TawwpExecuteReport _tawwpExecuteReport);

	/**
	 * 删除执行作业计划周、月报
	 * 
	 * @param _tawwpExecuteReport
	 *            TawwpExecuteReport 执行作业计划周、月报类
	 * @throws Exception
	 *             操作异常
	 */
	public void deleteExecuteReport(TawwpExecuteReport _tawwpExecuteReport);

	/**
	 * 修改执行作业计划周、月报
	 * 
	 * @param _tawwpExecuteReport
	 *            TawwpExecuteReport 执行作业计划周、月报类
	 * @throws Exception
	 *             操作异常
	 */
	public void updateExecuteReport(TawwpExecuteReport _tawwpExecuteReport);

	/**
	 * 查询执行作业计划周、月报信息
	 * 
	 * @param id
	 *            String 执行作业计划周、月报标识
	 * @throws Exception
	 *             操作异常
	 * @return TawwpExecuteReport 执行作业计划周、月报类
	 */
	public TawwpExecuteReport loadExecuteReport(String id);

	/**
	 * 查询所有执行作业计划周、月报信息
	 * 
	 * @throws Exception
	 *             操作异常
	 * @return List 执行作业计划周、月报类列表
	 */
	public List listExecuteReport() throws Exception;

	/**
	 * 查询属于指定月度作业计划的所有周、月报信息
	 * 
	 * @param _tawwpMonthPlan
	 *            TawwpMonthPlan 月度作业计划
	 * @throws Exception
	 *             操作异常
	 * @return List 周、月报信息
	 */
	public List listExecuteReport(TawwpMonthPlan _tawwpMonthPlan);
}
