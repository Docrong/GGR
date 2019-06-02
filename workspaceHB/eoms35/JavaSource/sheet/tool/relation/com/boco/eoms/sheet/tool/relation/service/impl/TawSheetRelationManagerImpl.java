package com.boco.eoms.sheet.tool.relation.service.impl;

import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;

import com.boco.eoms.sheet.tool.relation.dao.ITawSheetRelationDao;
import com.boco.eoms.sheet.tool.relation.dao.ITawSheetRelationDaoJdbc;
import com.boco.eoms.sheet.tool.relation.model.TawSheetRelation;
import com.boco.eoms.sheet.tool.relation.service.ITawSheetRelationManager;

public class TawSheetRelationManagerImpl extends BaseManager implements
		ITawSheetRelationManager {

	private ITawSheetRelationDao dao;

	private ITawSheetRelationDaoJdbc tawSheetRelationDaoJdbc;

	public ITawSheetRelationDao getDao() {
		return dao;
	}

	public void setDao(ITawSheetRelationDao dao) {
		this.dao = dao;
	}

	public ITawSheetRelationDaoJdbc getTawSheetRelationDaoJdbc() {
		return tawSheetRelationDaoJdbc;
	}

	public void setTawSheetRelationDaoJdbc(
			ITawSheetRelationDaoJdbc tawSheetRelationDaoJdbc) {
		this.tawSheetRelationDaoJdbc = tawSheetRelationDaoJdbc;
	}

	/**
	 * 查询主调流程或被调流程是 sheetKey 的所有工单
	 * 
	 * @param sheetKey
	 * @return
	 */
	public TawSheetRelation getRelationSheetByCurrentId(String sheetKey) {
		TawSheetRelation tawSheetRelation = dao.getRelationSheetByCurrentId(sheetKey);
		return tawSheetRelation;
	}

	/**
	 * 查询主调流程是 sheetKey 的所有工单
	 * 
	 * @param sheetKey
	 * @return
	 */
	public List getRelationSheetByParentId(String sheetKey) {
		List list = dao.getRelationSheetByParentId(sheetKey);
		return list;
	}

	/**
	 * 查询被调流程是 sheetKey 的所有工单
	 * 
	 * @param sheetKey 
	 * @return
	 */
	public List showInvokeRelationShipList(String sheetKey) {
		List list =  dao.showInvokeRelationShipList(sheetKey);
		return list;
	}
	
	public List getRunningSheetByParentId(String sheetKey){
		List list = dao.getRunningSheetByParentId(sheetKey);
		return list;
	}

	public List getAllSheetByParentIdAndPhaseId(String sheetKey, String phaseId) {
		List list =  dao.getAllSheetByParentIdAndPhaseId(sheetKey,phaseId);
		return list;
	}

	public List getCloseSheetByParentIdAndPhaseId(String sheetKey, String phaseId) {
		List list =  dao.getCloseSheetByParentIdAndPhaseId(sheetKey,phaseId);
		return list;
	}

	public List getRunningSheetByParentIdAndPhaseId(String sheetKey, String phaseId) {
		List list =  dao.getRunningSheetByParentIdAndPhaseId(sheetKey,phaseId);
		return list;
	}
	/**
	 * 查詢主调流程ID是 sheetKey,操作步驟是phaseId 调用人是userId的 还未回复的工單
	 * @param sheetKey
	 * @param phaseId
	 * @return
	 */
	public List getRunningSheetByPidAndPhaseIdAndUserId(String sheetKey ,String phaseId, String userId){
		List list = dao.getRunningSheetByPidAndPhaseIdAndUserId(sheetKey, phaseId, userId);
		return list;
	}
	public List getSheetByProcessIdAndUserId(String parentId, String phaseId, String userId, String processId){
		List list = dao.getSheetByProcessIdAndUserId(parentId, phaseId, userId , processId);
		return list;
	}
}
