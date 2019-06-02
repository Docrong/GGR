package com.boco.eoms.sheet.tool.relation.dao;

import java.util.List;
import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.sheet.tool.relation.model.TawSheetRelation;

public interface ITawSheetRelationDao extends Dao {

	/**
	 * 查询与sheetKey相关的互调流程
	 * 
	 * @param sheetId
	 * @return
	 */
	public List showInvokeRelationShipList(String sheetKey);

	/**
	 * 查询与sheetId相关的互调流程
	 * 
	 * @param sheetId
	 * @return
	 */
	public List showInvokeRelationShipListBySheetId(String sheetId);

	/**
	 * 查询主调流程是 parentId 的所有工单
	 * 
	 * @param parentId
	 * @return
	 */
	public List getRelationSheetByParentId(String parentId);

	/**
	 * 查询主调流程是 parentSheetId 的所有工单
	 * 
	 * @param parentSheetId
	 * @return
	 */
	public List getRelationSheetByParentSheetId(String parentSheetId);

	/**
	 * 查询被调流程是 currentId 的所有工单
	 * 
	 * @param currentSheetId
	 * @return
	 */
	public TawSheetRelation getRelationSheetByCurrentId(String currentId);

	/**
	 * 查询被调流程是 currentSheetId 的所有工单
	 * 
	 * @param currentSheetId
	 * @return
	 */
	public List getRelationSheetByCurrentSheetId(String currentSheetId);
	
	public List getRunningSheetByParentId(String sheetKey);
	/**
	 * 查詢主调流程ID是 sheetKey,操作步驟是phaseId 的所有工單
	 * @param sheetKey
	 * @param phaseId
	 * @return
	 */
	public List getAllSheetByParentIdAndPhaseId(String sheetKey ,String phaseId);
	/**
	 * 查詢主调流程ID是 sheetKey,操作步驟是phaseId 的還沒回復工單
	 * @param sheetKey
	 * @param phaseId
	 * @return
	 */
	public List getRunningSheetByParentIdAndPhaseId(String sheetKey ,String phaseId);
	/**
	 * 查詢主调流程ID是 sheetKey,操作步驟是phaseId 的已經回復工單
	 * @param sheetKey
	 * @param phaseId
	 * @return
	 */
	public List getCloseSheetByParentIdAndPhaseId(String sheetKey ,String phaseId);
	/**
	 * 查詢主调流程ID是 sheetKey,操作步驟是phaseId 调用人是userId的 还未回复的工單
	 * @param sheetKey
	 * @param phaseId
	 * @return
	 */
	public List getRunningSheetByPidAndPhaseIdAndUserId(String sheetKey ,String phaseId, String userId);
	public List getSheetByProcessIdAndUserId(String parentId, String phaseId, String userId, String processId);
	
}
