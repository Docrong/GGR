package com.boco.eoms.workplan.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.workplan.dao.ITawwpModelPlanDao;
import com.boco.eoms.workplan.model.TawwpModelPlan;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 12:21:24 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class TawwpModelPlanDaoHibernate extends BaseDaoHibernate implements
		ITawwpModelPlanDao {
	/**
	 * 保存作业计划模板
	 * 
	 * @param _tawwpModelPlan
	 * TawwpModelPlan 作业计划模板类 @ 操作异常
	 */
	public void saveModelPlan(TawwpModelPlan _tawwpModelPlan) {
		this.getHibernateTemplate().save(_tawwpModelPlan);
	}
  
	/**
	 * 删除作业计划模板
	 * 
	 * @param _tawwpModelPlan
	 * TawwpModelPlan 作业计划模板类 @ 操作异常
	 */
	public void deleteModelPlan(TawwpModelPlan _tawwpModelPlan) {
		this.getHibernateTemplate().delete(_tawwpModelPlan);

	}

	/**
	 * 修改作业计划模板
	 * 
	 * @param _tawwpModelPlan
	 * TawwpModelPlan 作业计划模板类 @ 操作异常
	 */
	public void updateModelPlan(TawwpModelPlan _tawwpModelPlan) {
		// this.update(_tawwpModelPlan);
		this.getHibernateTemplate().update(_tawwpModelPlan);

	}

	/**
	 * 查询作业计划模板信息
	 * 
	 * @param id
	 * String 作业计划模板标识 @ 操作异常
	 * @return TawwpModelPlan 作业计划模板类
	 */
	public TawwpModelPlan loadModelPlan(String id) {
		// return (TawwpModelPlan) this.load(id, TawwpModelPlan.class);

		return (TawwpModelPlan) this.getHibernateTemplate().get(
				TawwpModelPlan.class, id);
	}

	/**
	 * 查询所有作业计划模板信息 @ 操作异常
	 * @return List 作业计划模板类列表
	 */
	public List listModelPlan() {
		// Session s = HibernateUtil.currentSession();
		// String hSql = "";
		// hSql = "from TawwpModelPlan as tawwpmodelplan where
		// tawwpmodelplan.deleted = 0 ";
		// s.clear();
		// Query query = s.createQuery(hSql);
		// return query.list();
		return this
				.getHibernateTemplate()
				.find(
						"from TawwpModelPlan as tawwpmodelplan where tawwpmodelplan.deleted = 0 order by tawwpmodelplan.name ");

	}

	/**
	 * 查询所有作业计划模板信息，按网元类别查询
	 * 
	 * @param _netTypeId
	 * String 网元类别 @ 操作异常
	 * @return List 作业计划模板类列表
	 */
	public List listModelPlan(String _netTypeId) {
		// Session s = HibernateUtil.currentSession();
		//
		// String hSql = "";
		// hSql = "from TawwpModelPlan as tawwpmodelplan where
		// tawwpmodelplan.deleted = '0' and tawwpmodelplan.netTypeId = '"
		// + _netTypeId + "'";
		// s.clear();
		// Query query = s.createQuery(hSql);
		// return query.list();
		return getHibernateTemplate()
				.find(
						"from TawwpModelPlan as tawwpmodelplan where tawwpmodelplan.deleted = '0' and tawwpmodelplan.netTypeId = '"
								+ _netTypeId + "'");
	}

	/**
	 * 通过作业计划模板编号获取作业计划模板对象集合
	 * 
	 * @param _modelPlanIdList
	 * String 作业计划模板编号字符串 @ 操作异常
	 * @return List 作业计划模板类列表
	 */
	public List listModelPlanByModelPlanIds(String _modelPlanIdList) {
		String modelplanIdArray[] = null;
		modelplanIdArray = _modelPlanIdList.split(",");
		List list = new ArrayList();

		for (int i = 0; i < modelplanIdArray.length; i++) {
			list.add(this.loadModelPlan(modelplanIdArray[i]));
		}
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.boco.eoms.workplan.dao.ITawwpModelPlanDao#getDictIdByName(java.lang.String)
	 */
	// 系统类型。
	public String getDictIdByName(String dictName){
		String sql = "from TawSystemDictType as dicttype where dicttype.dictName='"+dictName+"' and  dicttype.parentDictId = '50'";
		List list = new ArrayList();
		list = getHibernateTemplate().find(sql);
		String dictId="";
		if(list.size()>0){
			TawSystemDictType dict = (TawSystemDictType)list.get(0);
			dictId = dict.getDictId();
		}
		return dictId;
	}
	
	
	/**
	 * @param dictName
	 * @param sysTypeId
	 * @return
	 */
	public String getDictIdByNameAndSysId(String dictName,String sysTypeId){
		String sql = "from TawSystemDictType as dicttype where dicttype.dictName='"+dictName+"' and dicttype.parentDictId='"+sysTypeId+"'";
		List list = new ArrayList();
		list = getHibernateTemplate().find(sql);
		String dictId="";
		if(list.size()>0){
			TawSystemDictType dict = (TawSystemDictType)list.get(0);
			dictId = dict.getDictId();
		}
		return dictId;
	}
}
