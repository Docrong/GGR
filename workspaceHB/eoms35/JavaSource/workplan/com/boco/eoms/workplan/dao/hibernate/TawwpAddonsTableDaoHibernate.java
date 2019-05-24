package com.boco.eoms.workplan.dao.hibernate;

import java.util.List;
 

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
 
import com.boco.eoms.workplan.dao.ITawwpAddonsTableDao; 
import com.boco.eoms.workplan.model.TawwpAddonsTable;
 

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 25, 2008 10:02:49 PM
 * </p>
 * 
 * @author eoms
 * @version 3.5.1
 * 
 */
public class TawwpAddonsTableDaoHibernate extends BaseDaoHibernate implements
		ITawwpAddonsTableDao {
	/**
	   * 保存附加表
	   * @param _tawwpAddonsTable TawwpAddonsTable 附加表类
	   * @throws Exception 操作异常
	   */
	  public void saveAddonsTable(TawwpAddonsTable _tawwpAddonsTable)  {
		  getHibernateTemplate().save(_tawwpAddonsTable);
	  }

	  /**
	   * 删除附加表
	   * @param _tawwpAddonsTable TawwpAddonsTable 附加表类
	   * @throws Exception 操作异常
	   */
	  public void deleteAddonsTable(TawwpAddonsTable _tawwpAddonsTable)  {
		  getHibernateTemplate().delete(_tawwpAddonsTable);
	  }

	  /**
	   * 修改附加表
	   * @param _tawwpAddonsTable TawwpAddonsTable 附加表类
	   * @throws Exception 操作异常
	   */
	  public void updateAddonsTable(TawwpAddonsTable _tawwpAddonsTable)  {
		  getHibernateTemplate().update(_tawwpAddonsTable);
	  }

	  /**
	   * 查询附加表信息
	   * @param id String 年度作业计划标识
	   * @throws Exception 操作异常
	   * @return TawwpYearPlan 年度作业计划类
	   */
	  public TawwpAddonsTable loadAddonsTable(String id)  {
	    return (TawwpAddonsTable)getHibernateTemplate().get(TawwpAddonsTable.class,id);
	  }

	  /**
	   * 查询所有附加表
	   * @throws Exception 操作异常
	   * @return List 附加表类列表
	   */

	  public List listAddonsTable() { 
	    String hSql = "";
	    hSql =
	        "from TawwpAddonsTable as tawwpaddonstable";
	    return getHibernateTemplate().find(hSql);
	  }

	  /**
	   * 查询符合模块的附加表
	   * @param _model String 模版编号
	   * @throws Exception 操作异常
	   * @return List 附加表类列表
	   */
	  public List listAddonsTable(String _model) { 
	    String hSql = "";
	    hSql =
	        "from TawwpAddonsTable as tawwpaddonstable where tawwpaddonstable.model='" +
	        _model + "' order by name";
	    return getHibernateTemplate().find(hSql);
	  }

	 

}
