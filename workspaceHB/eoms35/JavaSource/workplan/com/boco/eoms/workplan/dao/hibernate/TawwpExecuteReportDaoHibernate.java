package com.boco.eoms.workplan.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.workplan.dao.ITawwpExecuteReportDao;
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
 * Date:Jun 25, 2008 11:22:32 PM
 * </p>
 * 
 * @author eoms
 * @version 3.5.1
 * 
 */
public class TawwpExecuteReportDaoHibernate extends BaseDaoHibernate implements
		ITawwpExecuteReportDao {

	/**
	 * 保存执行作业计划周、月报
	 * 
	 * @param _tawwpExecuteReport
	 *            TawwpExecuteReport 执行作业计划周、月报类
	 * @throws Exception
	 *             操作异常
	 */
	public void saveExecuteReport(TawwpExecuteReport _tawwpExecuteReport) {
		this.getHibernateTemplate().save(_tawwpExecuteReport);
	}

	/**
	 * 删除执行作业计划周、月报
	 * 
	 * @param _tawwpExecuteReport
	 *            TawwpExecuteReport 执行作业计划周、月报类
	 * @throws Exception
	 *             操作异常
	 */
	public void deleteExecuteReport(TawwpExecuteReport _tawwpExecuteReport) {
		this.getHibernateTemplate().delete(_tawwpExecuteReport);
	}

	/**
	 * 修改执行作业计划周、月报
	 * 
	 * @param _tawwpExecuteReport
	 *            TawwpExecuteReport 执行作业计划周、月报类
	 * @throws Exception
	 *             操作异常
	 */
	public void updateExecuteReport(TawwpExecuteReport _tawwpExecuteReport) {
		this.getHibernateTemplate().update(_tawwpExecuteReport);
	}

	/**
	 * 查询执行作业计划周、月报信息
	 * 
	 * @param id
	 *            String 执行作业计划周、月报标识
	 * @throws Exception
	 *             操作异常
	 * @return TawwpExecuteReport 执行作业计划周、月报类
	 */
	public TawwpExecuteReport loadExecuteReport(String id) {

		return (TawwpExecuteReport) getHibernateTemplate().get(
				TawwpExecuteReport.class, id);
	}

	/**
	 * 查询所有执行作业计划周、月报信息
	 * 
	 * @throws Exception
	 *             操作异常
	 * @return List 执行作业计划周、月报类列表
	 */
	public List listExecuteReport() {

		return this.getHibernateTemplate().find(
				"from TawwpExecuteReport as tawwpexecutereport");
		 
	}

	/**
	 * 查询属于指定月度作业计划的所有周、月报信息
	 * 
	 * @param _tawwpMonthPlan
	 *            TawwpMonthPlan 月度作业计划
	 * @throws Exception
	 *             操作异常
	 * @return List 周、月报信息
	 */
	public List listExecuteReport(TawwpMonthPlan _tawwpMonthPlan) {
	 
		DetachedCriteria criteria = DetachedCriteria.forClass(
				TawwpExecuteReport.class).add(
				Expression.eq("tawwpMonthPlan", _tawwpMonthPlan));
		return getHibernateTemplate().findByCriteria(criteria);
	}

}
