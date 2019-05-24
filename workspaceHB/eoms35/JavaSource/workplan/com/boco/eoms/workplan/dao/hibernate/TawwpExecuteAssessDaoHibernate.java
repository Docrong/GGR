package com.boco.eoms.workplan.dao.hibernate;

import java.util.List;
 
import org.hibernate.criterion.DetachedCriteria;
 
import org.hibernate.criterion.Expression;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
 
import com.boco.eoms.workplan.dao.ITawwpExecuteAssessDao;
import com.boco.eoms.workplan.model.TawwpExecuteAssess;
import com.boco.eoms.workplan.model.TawwpMonthPlan;
 
public class TawwpExecuteAssessDaoHibernate extends BaseDaoHibernate implements
   ITawwpExecuteAssessDao  {
	/**
	   * 保存执行作业计划考核
	   * @param _tawwpExecuteAssess TawwpExecuteAssess 执行作业计划考核类
	   * @throws Exception 操作异常
	   */
	  public void saveExecuteAssess(TawwpExecuteAssess _tawwpExecuteAssess)  {
		  getHibernateTemplate().save(_tawwpExecuteAssess);
	  }
	 
	  /**
	   * 删除执行作业计划考核
	   * @param _tawwpExecuteAssess TawwpExecuteAssess 执行作业计划考核类
	   * @throws Exception 操作异常
	   */
	  public void deleteExecuteAssess(TawwpExecuteAssess _tawwpExecuteAssess) 
	       {
		  getHibernateTemplate().delete(_tawwpExecuteAssess);
	  }

	  /**
	   * 修改执行作业计划考核
	   * @param _tawwpExecuteAssess TawwpExecuteAssess 执行作业计划考核类
	   * @throws Exception 操作异常
	   */
	  public void updateExecuteAssess(TawwpExecuteAssess _tawwpExecuteAssess)  {
		  getHibernateTemplate().update(_tawwpExecuteAssess);
	  }

	  /**
	   * 查询执行作业计划考核信息
	   * @param id String 执行作业计划考核标识
	   * @throws Exception 操作异常
	   * @return TawwpExecuteAssess 执行作业计划考核类
	   */
	  public TawwpExecuteAssess loadExecuteAssess(String id) {
	    return (TawwpExecuteAssess)getHibernateTemplate().get(TawwpExecuteAssess.class,id);
	  }

	  /**
	   * 查询所有执行作业计划考核信息
	   * @throws Exception 操作异常
	   * @return List 执行作业计划考核类列表
	   */
	  public List listExecuteAssess() {
	    
	    String hSql =
	        "from TawwpExecuteAssess as tawwpexecuteassess";
	    return getHibernateTemplate().find(hSql);
	  }

	  /**
	   * 查询属于指定月度作业计划的所有考核信息
	   * @param _tawwpMonthPlan TawwpMonthPlan 月度作业计划
	   * @throws Exception 操作异常
	   * @return List 考核信息列表
	   */
	  public List listExecuteAssess(TawwpMonthPlan _tawwpMonthPlan)  {
		  DetachedCriteria criteria=DetachedCriteria.forClass(TawwpExecuteAssess.class).add(Expression.eq("tawwpMonthPlan", _tawwpMonthPlan)) ;
		  getHibernateTemplate().findByCriteria(criteria);
	      return getHibernateTemplate().findByCriteria(criteria);
	  }

	  /**
	   * 查询需要当前用户进行考核的月度作业计划,相应的考核信息
	   * @param _userId String
	   * @throws Exception
	   * @return List
	   */
	  public List listExecuteAssessByUser(String _userId) {
	    String hSql = "";
	    hSql =
	        "from TawwpExecuteAssess as tawwpexecuteassess "
	        + "where tawwpexecuteassess.checkUser like '%" + _userId
	        + "%' and tawwpexecuteassess.state = '0'";
 
	    return getHibernateTemplate().find(hSql);
	  }
}
