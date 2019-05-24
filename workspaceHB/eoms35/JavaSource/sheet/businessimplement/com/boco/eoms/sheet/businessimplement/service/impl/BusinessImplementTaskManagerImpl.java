
package com.boco.eoms.sheet.businessimplement.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.impl.TaskServiceImpl;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.businessimplement.dao.IBusinessImplementTaskDAO;
import com.boco.eoms.sheet.businessimplement.service.IBusinessImplementTaskManager;
//import com.boco.eoms.sheet.base.util.QuerySqlInit;
import com.boco.eoms.sheet.commonfault.dao.ICommonFaultMainDAO;
import com.boco.eoms.sheet.overtimetip.util.OvertimeTipUtil;

public class BusinessImplementTaskManagerImpl extends TaskServiceImpl implements  IBusinessImplementTaskManager{

	public Integer getCountOfBrother(String sheetKey, String parentLevelId) throws SheetException {
		IBusinessImplementTaskDAO ibusinessimplementTaskDAO = (IBusinessImplementTaskDAO)this.getTaskDAO();	
		
		Integer count = new Integer(0);
		try {
			count = ibusinessimplementTaskDAO.getCountOfBrother(this.getTaskModelObject(), sheetKey, parentLevelId);
		} catch (Exception e) {
			throw new SheetException(e);
		}
		return count;
		}
	
	/**
	 * 获取当前角色未挂起待处理工单,带超时提醒
	 * @param userId 用户ID
	 * @param deptId 部门ID 
	 * @param flowName 流程ID
	 * @param startIndex 
	 * @param length
	 * @return
	 * @throws Exception
	 */
//	public  HashMap getUndoTaskByOverTime(Map condition,String userId,String deptId,String flowName,Integer startIndex,Integer length) throws Exception{
//		HashMap taskMap=new HashMap();
//		HashMap cloumnMap = OvertimeTipUtil.getMainColumnByMapping(flowName);
//		Iterator it = cloumnMap.keySet().iterator();
//		String ruternColumnName = "";
//		while(it.hasNext()){
//			String tmpOvertimeClm = (String)it.next();
//			ruternColumnName+=" main."+(String)cloumnMap.get(tmpOvertimeClm)+",";
//		}
//		if(!ruternColumnName.equals("")){
//			ruternColumnName = ","+ruternColumnName.substring(0,ruternColumnName.length()-1);
//		}
//		BaseMain mainObject = (BaseMain)condition.get("mainObject");
//		String hql = "select distinct task" + ruternColumnName + " from "
//				+ this.getTaskModelObject().getClass().getName()+ " as task,"
//				+ mainObject.getClass().getName()+ " as main "
//				+ "where ((task.taskOwner='" + userId + "' or task.taskOwner='" + deptId + "')"
//				+ " or task.taskOwner in (select userrefrole.subRoleid from TawSystemUserRefRole as userrefrole where userrefrole.userid='" + userId + "'))"
//				+ " and (task.taskStatus=" + Constants.TASK_STATUS_READY 
//				+ " or task.taskStatus=" + Constants.TASK_STATUS_CLAIMED
//				+ " or task.taskStatus=" + Constants.TASK_STATUS_INACTIVE + ")"
//				+ " and task.taskDisplayName<>'草稿'"
//				+ " and main.id=task.sheetKey and main.deleted<>'1' and main.status<>'5'"
//				+ " and (task.ifWaitForSubTask='false' or (task.ifWaitForSubTask='true' and task.id in (select task1.parentTaskId from "+this.getTaskModelObject().getClass().getName()+" as task1 where task1.subTaskFlag='true' and (task1.subTaskDealFalg='false' or task1.subTaskDealFalg is null ) and task1.taskStatus='"+Constants.TASK_STATUS_FINISHED+"')))"
//				+ " order by task.createTime desc";
//		IBusinessImplementTaskDAO iBusinessImplementTaskDAO = (IBusinessImplementTaskDAO)this.getTaskDAO();
//		taskMap=iBusinessImplementTaskDAO.getTaskListByCondition(hql,startIndex,length);
//		return taskMap;
//	}
}
