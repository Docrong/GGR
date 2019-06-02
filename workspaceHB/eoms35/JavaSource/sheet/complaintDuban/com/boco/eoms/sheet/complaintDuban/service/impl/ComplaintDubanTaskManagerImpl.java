
package com.boco.eoms.sheet.complaintDuban.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.boco.eoms.sheet.base.service.impl.TaskServiceImpl;
import com.boco.eoms.sheet.complaintDuban.dao.IComplaintDubanTaskDAO;
import com.boco.eoms.sheet.complaintDuban.service.IComplaintDubanTaskManager;


public class ComplaintDubanTaskManagerImpl extends TaskServiceImpl implements  IComplaintDubanTaskManager{
	public HashMap getUndoTaskByOverTime(Map condition, String userId, String deptId, String flowName, Integer startIndex, Integer length)
	throws Exception
		{
			HashMap taskMap = new HashMap();
			String sql = "";
			String countsql = "";
				sql = "select distinct task.id,task.TASKNAME,task.correlationKey,task.levelId,task.parentLevelId,task.TASKDISPLAYNAME,NULL CREATETIME,task.TASKSTATUS," +
						" '' PROCESSID,task.SHEETKEY,task.SHEETID,task.ACCEPTTIMELIMIT,task.COMPLETETIMELIMIT,task.OPERATEROLEID,task.TASKOWNER,'' PRELINKID,'' FLOWNAME," +
						" '' CURRENTLINKID,'' OPERATETYPE,'' PARENTTASKID,'' SUBTASKDEALFALG,task.IFWAITFORSUBTASK,'' SUBTASKFLAG,0 CREATEYEAR,0 CREATEMONTH,0 CREATEDAY," +
						" task.SENDTIME,'' PREDEALDEPT,'' PREDEALUSERID,'' PARENTPROCESSNAME,'' preDealRoleId,'' replaceWaitForSubTask,main.title" + 
				" from complaintduban_main main,complaintduban_task task where main.deleted =0 and main.id = task.sheetkey and " +
				"task.taskowner in ('" + userId + "', '" + deptId + "') and task.taskstatus in ('2','8') " +
						"and task.taskDisplayName <> '草稿' and task.taskDisplayName <> '阶段回复' and task.ifwaitforsubtask = 'false'";
				countsql = " select count(*) from (" + sql +")";
				StringBuffer hql = new StringBuffer(sql);
				StringBuffer counthql = new StringBuffer(countsql);
				hql.append(" order by task.sendtime desc");
				Integer total = ((IComplaintDubanTaskDAO)getTaskDAO()).getCountTaskBySQL(counthql.toString());
				taskMap = ((IComplaintDubanTaskDAO)getTaskDAO()).getTaskListBySQL(hql.toString(), startIndex, length);
				taskMap.put("taskTotal", total);
			return taskMap;
			
//			HashMap taskMap = new HashMap();
//			String orderCondition = (String)condition.get("orderCondition");
//			BaseMain mainObject = (BaseMain)condition.get("mainObject");
//			ITask taskObject = (ITask)condition.get("taskObject");
//			HashMap cloumnMap = OvertimeTipUtil.getAllMainColumnByMapping(flowName);
//			Iterator it = cloumnMap.keySet().iterator();
//			String ruternColumnName;
//			String tmpOvertimeClm;
//			for (ruternColumnName = ""; it.hasNext(); ruternColumnName = ruternColumnName + " main." + (String)cloumnMap.get(tmpOvertimeClm) + ",")
//				tmpOvertimeClm = (String)it.next();
//
//			if (!ruternColumnName.equals(""))
//				ruternColumnName = "," + ruternColumnName.substring(0, ruternColumnName.length() - 1);
//			System.out.println("wanghao1====cloumnMap:" + cloumnMap.size() + "============ruternColumnName:" + ruternColumnName);
//			String sql = "select distinct task" + ruternColumnName + " from " + taskObject.getClass().getName() + " as task," + mainObject.getClass().getName() 
//			+ " as main " + "where ((task.taskOwner='" + userId + "' or task.operateRoleId='" + deptId + "')" + " " +
//					"or task.operateRoleId in (select userrefrole.subRoleid from TawSystemUserRefRole as userrefrole where userrefrole.userid='" + userId + "'))" + "" +
//							" and (task.taskStatus=" + "2" + " or task.taskStatus=" + "8" + " or task.taskStatus=" + "1" + ")" 
//							+ " and task.taskDisplayName<>'²ݸ㥢 + " and main.id=task.sheetKey and main.deleted<>'1'" + " and (task.ifWaitForSubTask='false' or (task.ifWaitForSubTask='true' and task.id in (select task1.parentTaskId from " + taskObject.getClass().getName() + " as task1 where task1.subTaskFlag='true' and (task1.subTaskDealFalg='false' or task1.subTaskDealFalg is null ) and task1.taskStatus='" + "5" + "')))";
//			StringBuffer hql = new StringBuffer(sql);
//			System.out.println("wanghao1====sql:" + sql);
//			if (length.intValue() != -1)
//				if (!orderCondition.equals("") && orderCondition != null)
//					hql.append("order by " + orderCondition);
//				else
//					hql.append("order by task.createTime desc");
//			taskMap = getTaskDAO().getTaskListByCondition(hql.toString(), startIndex, length);
//			return taskMap;
			
		}
  }
