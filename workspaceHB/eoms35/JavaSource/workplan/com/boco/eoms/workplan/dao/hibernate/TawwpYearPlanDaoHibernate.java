package com.boco.eoms.workplan.dao.hibernate;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
 
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.common.util.StaticMethod;
 
import com.boco.eoms.workplan.dao.ITawwpYearPlanDao;
import com.boco.eoms.workplan.model.TawwpExecuteAssess;
import com.boco.eoms.workplan.model.TawwpModelPlan;
import com.boco.eoms.workplan.model.TawwpYearPlan;

public class TawwpYearPlanDaoHibernate extends BaseDaoHibernate implements ITawwpYearPlanDao {
	/**
	   * 保存年度作业计划
	   * @param _tawwpYearPlan TawwpYearPlan 年度作业计划类
	   * @  操作异常
	   */
	  public void saveYearPlan(TawwpYearPlan _tawwpYearPlan)   {
	    this.getHibernateTemplate().save(_tawwpYearPlan);
	  }

	  /**
	   * 删除年度作业计划
	   * @param _tawwpYearPlan TawwpYearPlan 年度作业计划类
	   * @  操作异常
	   */
	  public void deleteYearPlan(TawwpYearPlan _tawwpYearPlan)   {
		  getHibernateTemplate().delete(_tawwpYearPlan);
	    
	  }

	  /**
	   * 修改年度作业计划
	   * @param _tawwpYearPlan TawwpYearPlan 年度作业计划类
	   * @  操作异常
	   */
	  public void updateYearPlan(TawwpYearPlan _tawwpYearPlan)   {
		  getHibernateTemplate().update(_tawwpYearPlan);
	   
	  }

	  /**
	   * 查询年度作业计划信息
	   * @param id String 年度作业计划标识
	   * @  操作异常
	   * @return TawwpYearPlan 年度作业计划类
	   */
	  public TawwpYearPlan loadYearPlan(String id)   {
	    return (TawwpYearPlan)getHibernateTemplate().get(TawwpYearPlan.class,id);
	  }
	   
	  /**
	   * 查询所有年度作业计划信息
	   * @  操作异常
	   * @return List 年度作业计划类列表
	   */
	  public List listYearPlan()   {
	    
	    String hSql = "";
	    hSql =
	        "from TawwpYearPlan as tawwpyearplan where tawwpyearplan.deleted = '0' order by tawwpyearplan.crtime ";

	    
	    return getHibernateTemplate().find(hSql);
	  }

	  /**
	   * 获取部门的年度作业计划列表
	   * @param _deptId String 部门编号
	   * @param _yearFlag String 年度
	   * @  操作异常
	   * @return List 年度作业计划类列表
	   */
	  public List listYearPlanByDept(String _deptId,String _yearFlag)   {
	    
	    String hSql =
	        "from TawwpYearPlan as tawwpyearplan where tawwpyearplan.deleted = '0' "
	        + "and tawwpyearplan.deptId = '" + _deptId + "' "
	        + "and tawwpyearplan.yearFlag = '" + _yearFlag + "' "
	        + "and tawwpyearplan.state = '1' order by tawwpyearplan.crtime ";

	    
	    return getHibernateTemplate().find(hSql);
	  }

	  /**
	   * 获取部门的年度作业计划列表
	   * @param _deptId String 部门编号
	   * @param _yearFlag String 年度
	   * @  操作异常
	   * @return List 年度作业计划类列表
	   */
	  public List listYearPlanByYearDept(String _deptId,String _yearFlag)   {
	     
	    String hSql =
	        "from TawwpYearPlan as tawwpyearplan where tawwpyearplan.deleted = '0' "
	        + "and tawwpyearplan.deptId = '" + _deptId + "' "
	        + "and tawwpyearplan.yearFlag = '" + _yearFlag + "'  order by tawwpyearplan.name ";

	    
	    return getHibernateTemplate().find(hSql);
	  }

	  /**
	   * 获取部门的年度作业计划列表数量
	   * @param _deptId String 部门编号
	   * @param _yearFlag String 年度
	   * @param _tawwpModelPlan TawwpModelPlan 作业计划模版对象
	   * @  操作异常
	   * @return int 年度作业计划类列表数量
	   */
	  public int countYearplanByYearDept(String _deptId,String _yearFlag,TawwpModelPlan _tawwpModelPlan)   {
		   
	  
	      DetachedCriteria criteria=DetachedCriteria.forClass(TawwpYearPlan.class).add(Expression.eq("deptId", _deptId)).add(Expression.eq("yearFlag", _yearFlag))
	      .add(Expression.eq("tawwpModelPlan", _tawwpModelPlan)).add(Expression.eq("deleted", "0"));
	      getHibernateTemplate().findByCriteria(criteria);
	      return getHibernateTemplate().findByCriteria(criteria).size();

	  }


	  /**
	   * 根据查询信息map，查询符合条件的月度作业计划
	   * 条件包括 name 年度作业计划名称
	   *         deptId 部门
	   *         sysTypeId 系统类别
	   *         netTypeId 网元类型
	   *         yearFlag  年度
	   *         state 制定状态
	   * @param _map Map 查询条件map
	   * @  操作异常
	   * @return List 月度作业计划列表
	   */
	  public List searchYearPlan(Map _map)   {
		DetachedCriteria criteria=DetachedCriteria.forClass(TawwpYearPlan.class);
	    String condition = null;
	    List modelList = null;

	    //获取条件信息列表
	    Set set = _map.keySet();
	    Iterator iterator = set.iterator();

	    //循环增加查询条件
	    while (iterator.hasNext()) {
	      condition = (String) iterator.next(); //获取一个查询提交
	      if (condition != null) {
	        if (condition != null) {
	          if (condition.equals("name")) {
	            criteria.add(Expression.like(condition, _map.get(condition)));
	          }
	          else if (condition.equals("modelId")) {
	            modelList = (List) _map.get(condition);
	            criteria.add(Expression.in("tawwpModelPlan", modelList));
	          }
	          else {
	            criteria.add(Expression.eq(condition, _map.get(condition)));
	          }
	        }

	      }
	    }
	    return getHibernateTemplate().findByCriteria(criteria); //获取查询结果
	  }
	  public List searchYearPlanLink(Map _mapQuery)   {
		  String yearFlag,sysTypeId,netTypeId,state,linkId ,deptId= "";
		  List modelId = null;
		  if(_mapQuery.get("modelId") != null){
			  modelId = (List) _mapQuery.get("modelId");
		  }
		  yearFlag = (String) _mapQuery.get("yearFlag");
		  sysTypeId = (String) _mapQuery.get("sysTypeId");
		  netTypeId = (String) _mapQuery.get("netTypeId");
		  state = (String) _mapQuery.get("state");
		  deptId = (String) _mapQuery.get("deptId");
		  List deptIdlist = (List) _mapQuery.get("deptIdlist");
		  TawSystemDept tawSystemDept = new TawSystemDept();
		  String deptIds = "";
		  String modelIds = "";
		  if(deptIdlist!= null){
			  for(int i = 0;i<deptIdlist.size();i++){
				  tawSystemDept = (TawSystemDept)deptIdlist.get(i);
				  deptIds += "'"+tawSystemDept.getDeptId()+"',";
			  
			  }
			  if(!"".equals(deptIds)){
				  deptIds = deptIds.substring(0,deptIds.length()-1) ;
		  	  }
		  }
		  if(modelId!=null){
			  for(int j=0;j<modelId.size();j++){
				  TawwpModelPlan tawwpModelPlan = (TawwpModelPlan)modelId.get(j);
				  modelIds += "'" + tawwpModelPlan.getId() + "',";
			  }
			  if(!"".equals(modelIds)){
				  modelIds = modelIds.substring(0,modelIds.length()-1) ;
			  }
		  }
		  String hSql ="";
		     
		  hSql = "from TawwpYearPlan as tawwpyearplan where tawwpyearplan.deleted = '0' ";
		  if(modelId!=null){
			  hSql+= " and modelId in ("+modelIds+")";
		  }
		  if(yearFlag!=null&&!yearFlag.equals("")){
			  hSql+= " and yearFlag = '"+yearFlag+"'";
		  }
		  if(sysTypeId!=null&&!sysTypeId.equals("")){
			  hSql+= " and sysTypeId = '"+sysTypeId+"'";
		  }
		  if(netTypeId!=null&&!netTypeId.equals("")){
			  hSql+= " and netTypeId = '"+netTypeId+"'";
		  }
		  if(state!=null&&!state.equals("")){
			  hSql+= " and state = '"+state+"'";
		  }
		  if(deptId!=null&&!deptId.equals("")){
			  hSql+= " and deptId in ("+deptIds+")";
		  }
		    return getHibernateTemplate().find(hSql);
		  }
}
