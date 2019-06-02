package com.boco.eoms.sheet.tool.relation.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.tool.relation.dao.ITawSheetRelationDao;
import com.boco.eoms.sheet.tool.relation.model.TawSheetRelation;

public class TawSheetRelationDaoHibernate extends BaseDaoHibernate implements
		ITawSheetRelationDao {

	/**
	 * 查询与sheetKey相关的互调流程
	 * 
	 * @param sheetId
	 * @return
	 */
	public List showInvokeRelationShipList(String sheetKey) {
		String hql = " from TawSheetRelation obj where obj.parentId = '"
				+ sheetKey + "' or obj.currentId = '" + sheetKey + "'";
		return (ArrayList) getHibernateTemplate().find(hql);
	}

	/**
	 * 查询与sheetId相关的互调流程
	 * 
	 * @param sheetId
	 * @return
	 */
	public List showInvokeRelationShipListBySheetId(String sheetId) {
		String hql = " from TawSheetRelation obj where obj.parentSheetId = '"
				+ sheetId + "' or obj.currentSheetId = '" + sheetId + "'";
		return (ArrayList) getHibernateTemplate().find(hql);
	}

	/**
	 * 查询主调流程是 parentId 的所有工单
	 * 
	 * @param parentId
	 * @return
	 */
	public List getRelationSheetByParentId(String parentId) {
		String hql = " from TawSheetRelation obj where obj.parentId = '"
				+ parentId + "'";
		return (ArrayList) getHibernateTemplate().find(hql);
	}

	/**
	 * 查询主调流程是 parentSheetId 的所有工单
	 * 
	 * @param parentSheetId
	 * @return
	 */
	public List getRelationSheetByParentSheetId(String parentSheetId) {
		String hql = " from TawSheetRelation obj where obj.parentSheetId = '"
				+ parentSheetId + "'";
		return (ArrayList) getHibernateTemplate().find(hql);
	}

	/**
	 * 查询被调流程是 currentId 的所有工单
	 * 
	 * @param currentId
	 * @return
	 */
	public TawSheetRelation getRelationSheetByCurrentId(String currentId) {
		TawSheetRelation tawSheetRelation=null;
		String hql = " from TawSheetRelation obj where obj.currentId = '"
				+ currentId + "'";
		List list=getHibernateTemplate().find(hql);
		if(!list.isEmpty()){
			 tawSheetRelation=(TawSheetRelation)list.get(0);
		}
		return tawSheetRelation;
	}

	/**
	 * 查询被调流程是 currentSheetId 的所有工单
	 * 
	 * @param currentSheetId
	 * @return
	 */
	public List getRelationSheetByCurrentSheetId(String currentSheetId) {
		String hql = " from TawSheetRelation obj where obj.currentSheetId = '"
				+ currentSheetId + "'";
		return (ArrayList) getHibernateTemplate().find(hql);
	}

	public List getRunningSheetByParentId(String parentId) {
		String hql = " from TawSheetRelation obj where obj.parentId = '"
			+ parentId + "' and obj.invokeState="+Constants.INVOKE_STATE_RUNNING;
	    return (ArrayList) getHibernateTemplate().find(hql);
	}

	public List getAllSheetByParentIdAndPhaseId(String parentId, String phaseId) {
		String hql = " from TawSheetRelation obj where obj.parentId = '"
			+ parentId + "' and obj.taskName='"+phaseId+"'";
	    return (ArrayList) getHibernateTemplate().find(hql);
	}

	public List getCloseSheetByParentIdAndPhaseId(String parentId, String phaseId) {
		String hql = " from TawSheetRelation obj where obj.parentId = '"
			+ parentId + "' and obj.invokeState="+Constants.INVOKE_STATE_FINISH+" and obj.taskName='"+phaseId+"'";
	    return (ArrayList) getHibernateTemplate().find(hql);
	}

	public List getRunningSheetByParentIdAndPhaseId(String parentId, String phaseId) {
		String hql = " from TawSheetRelation obj where obj.parentId = '"
			+ parentId + "' and obj.invokeState="+Constants.INVOKE_STATE_RUNNING+" and obj.taskName='"+phaseId+"'";
	    return (ArrayList) getHibernateTemplate().find(hql);
	}
	public List getRunningSheetByPidAndPhaseIdAndUserId(String parentId, String phaseId,String userId) {
		String hql = " from TawSheetRelation obj where obj.parentId = '"
			+ parentId + "' and obj.invokeState="+Constants.INVOKE_STATE_RUNNING+" and obj.taskName='"+ phaseId + "' and obj.userId = '"+userId +"'";
	 	return (ArrayList) getHibernateTemplate().find(hql);
	}
	public List getSheetByProcessIdAndUserId(String parentId, String phaseId, String userId, String processId) {
		String hql = " from TawSheetRelation obj where obj.parentId = '"
			+ parentId + "' and obj.invokeState="+Constants.INVOKE_STATE_RUNNING+" and obj.taskName='"+ phaseId + "' and obj.userId = '"+userId +"' and obj.parentProcessId = '"+processId +"'";
	  System.out.println("hql is==="+hql);
		return (ArrayList) getHibernateTemplate().find(hql);
	}
}
