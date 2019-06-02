package com.boco.eoms.workplan.dao.hibernate;

import java.util.List;
 
import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.workplan.dao.ITawwpYearExecuteDao;
import com.boco.eoms.workplan.model.TawwpYearExecute;
 

public class TawwpYearExecuteDaoHibernate extends BaseDaoHibernate implements ITawwpYearExecuteDao  {
	 /**
	   * 保存年度作业计划执行内容
	   * @param _tawwpYearExecute TawwpYearExecute 年度作业计划执行内容类
	   * @  操作异常
	   */
	  public void saveYearExecute(TawwpYearExecute _tawwpYearExecute) 
	       {
	    this.getHibernateTemplate().save(_tawwpYearExecute);
	  }

	  /**
	   * 删除年度作业计划执行内容
	   * @param _tawwpYearExecute TawwpYearExecute 年度作业计划执行内容类
	   * @  操作异常
	   */
	  public void deleteYearExecute(TawwpYearExecute _tawwpYearExecute) 
	       {
		  getHibernateTemplate().delete(_tawwpYearExecute);
	     
	  }

	  /**
	   * 修改年度作业计划执行内容
	   * @param _tawwpYearExecute TawwpYearExecute 年度作业计划执行内容类
	   * @  操作异常
	   */
	  public void updateYearExecute(TawwpYearExecute _tawwpYearExecute) 
	       {
		  getHibernateTemplate().update(_tawwpYearExecute);
	  }

	  /**
	   * 查询年度作业计划执行内容信息
	   * @param id String 年度作业计划执行内容标识
	   * @  操作异常
	   * @return TawwpYearExecute 年度作业计划执行内容类
	   */
	  public TawwpYearExecute loadYearExecute(String id)   {
	    return (TawwpYearExecute)getHibernateTemplate().get(TawwpYearExecute.class,id);
	  }
	 
	  /**
	   * 查询所有年度作业计划执行内容信息
	   * @  操作异常
	   * @return List 年度作业计划执行内容类列表
	   */
	  public List ListYearExecute()   {
	    String hSql = "";
	    hSql =
	        "from TawwpYearExecute as tawwpyearexecute where tawwpyearexecute.deleted = 0 ";

	    
	    return getHibernateTemplate().find(hSql);
	  }	
	

}
