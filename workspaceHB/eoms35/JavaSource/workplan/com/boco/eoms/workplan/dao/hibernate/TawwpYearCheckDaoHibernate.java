package com.boco.eoms.workplan.dao.hibernate;

import java.util.List;
 
import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.workplan.dao.ITawwpYearCheckDao;
import com.boco.eoms.workplan.model.TawwpYearCheck;

public class TawwpYearCheckDaoHibernate  extends BaseDaoHibernate implements ITawwpYearCheckDao  {
	/**
	   * 保存年度作业计划审批信息
	   * @param _tawwpYearCheck TawwpYearCheck 年度作业计划审批信息篿
	   * @  操作异常
	   */
	  public void saveYearCheck(TawwpYearCheck _tawwpYearCheck)   {
	    this.getHibernateTemplate().save(_tawwpYearCheck);
	  }

	  /**
	   * 删除年度作业计划审批信息
	   * @param _tawwpYearCheck TawwpYearCheck 年度作业计划审批信息篿
	   * @  操作异常
	   */
	  public void deleteYearCheck(TawwpYearCheck _tawwpYearCheck) 
	       {
		  getHibernateTemplate().delete(_tawwpYearCheck);
	  }

	  /**
	   * 修改年度作业计划审批信息
	   * @param _tawwpYearCheck TawwpYearCheck 年度作业计划审批信息篿
	   * @  操作异常
	   */
	  public void updateYearCheck(TawwpYearCheck _tawwpYearCheck) 
	       {
		  getHibernateTemplate().update(_tawwpYearCheck);
	  }

	  /**
	   * 查询年度作业计划审批信息
	   * @param id String 年度作业计划审批信息标识
	   * @  操作异常
	   * @return TawwpYearCheck 年度作业计划审批信息篿
	   */
	  public TawwpYearCheck loadYearCheck(String id)   {
	    return (TawwpYearCheck)getHibernateTemplate().get(TawwpYearCheck.class,id);
	  }

	  /**
	   * 查询所有年度作业计划审批信忿
	   * @  操作异常
	   * @return List 年度作业计划审批信息类列蟿
	   */
	  public List listYearCheck()   {
	    String hSql = "";
	    hSql =
	        "from TawwpYearCheck";
	    return getHibernateTemplate().find(hSql);
	  }

	  /**
	   * 查询需要当前用户进行审批的年度作业计划,相应的审批信忿
	   * @param _userId String
	   * @ 
	   * @return List
	   */
	  public List listYearCheck(String _userId)   {
	    
	    String hSql = "";
	    hSql =
	        "from TawwpYearCheck as tawwpyearcheck "
	        + "where tawwpyearcheck.checkUser like '%" + _userId
	        + "%' and tawwpyearcheck.state = '0'";
 
	    return getHibernateTemplate().find(hSql);
	  }
	  /**
	   * 查询所有未审批年度作业计划审批信息
	   * @  操作异常
	   * @return List 未审批年度作业计划审批信息类列表
	   */
	  public List listUnPassYearCheck()   {
	    String hSql = "";
	    hSql =
	        "from TawwpYearCheck as tawwpyearCheck where tawwpyearcheck.state = '0'";
	    return getHibernateTemplate().find(hSql);
	  }
}
