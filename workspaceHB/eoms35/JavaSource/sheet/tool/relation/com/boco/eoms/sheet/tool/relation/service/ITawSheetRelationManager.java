package com.boco.eoms.sheet.tool.relation.service;

import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.sheet.tool.relation.model.TawSheetRelation;

public interface ITawSheetRelationManager extends Manager {
	/**
	 * 查询主调流程或被调流程是 sheetKey 的所有工单
	 * 
	 * @param sheetKey
	 * @return
	 */
	public List showInvokeRelationShipList(String sheetKey);

	/**
	 * 查询主调流程是 sheetKey 的所有工单
	 * 
	 * @param sheetKey
	 * @return
	 */
	public List getRelationSheetByParentId(String sheetKey);

	/**
	 * 查询被调流程是 sheetKey 的所有工单
	 * 
	 * @param sheetKey
	 * @return
	 */
	public TawSheetRelation getRelationSheetByCurrentId(String sheetKey);
	/**
	 * 查詢主调流程ID是 sheetKey，并且還沒回復的所有工單
	 * @param sheetKey
	 * @return
	 */
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
