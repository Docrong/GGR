package com.boco.eoms.sheet.localCommonTask.service.impl;

import java.util.*;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.impl.TaskServiceImpl;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.localCommonTask.dao.ILocalCommonTaskTaskDAO;
import com.boco.eoms.sheet.localCommonTask.service.ILocalCommonTaskTaskManager;
import com.boco.eoms.sheet.overtimetip.util.OvertimeTipUtil;

/**
 * <p>
 * Title:码号资源申请工单
 * </p>
 * <p>
 * Description:码号资源申请工单
 * </p>
 * <p>
 * Mon Sep 27 18:07:01 CST 2010
 * </p>
 * 
 * @author liuyang
 * @version 3.5
 * 
 */
 
 public class LocalCommonTaskTaskManagerImpl extends TaskServiceImpl implements  ILocalCommonTaskTaskManager {
	 public HashMap getUndoTaskByOverTime(Map condition, String userId, String deptId, String flowName, Integer startIndex, Integer length)
		throws Exception
	{
		HashMap taskMap = new HashMap();
		String orderCondition = (String)condition.get("orderCondition");
		BaseMain mainObject = (BaseMain)condition.get("mainObject");
		ITask taskObject = (ITask)condition.get("taskObject");
		HashMap cloumnMap = OvertimeTipUtil.getAllMainColumnByMapping(flowName);
		Iterator it = cloumnMap.keySet().iterator();
		String ruternColumnName;
		String tmpOvertimeClm;
		for (ruternColumnName = ""; it.hasNext(); ruternColumnName = ruternColumnName + " main." + (String)cloumnMap.get(tmpOvertimeClm) + ",")
			tmpOvertimeClm = (String)it.next();

		if (!ruternColumnName.equals(""))
			ruternColumnName = "," + ruternColumnName.substring(0, ruternColumnName.length() - 1);
		String sql ="";
		String countsql ="";
		String type =  StaticMethod.nullObject2String(condition.get("type"));
		String sheettype = StaticMethod.nullObject2String(condition.get("sheettype"));
		if("1".equals(type)){
			sql = "select distinct task" + ruternColumnName + " from " + taskObject.getClass().getName()+" as task," + mainObject.getClass().getName() + " as main " + "where ((task.taskOwner='" + userId + "' or task.operateRoleId='" + deptId + "')" + " or task.operateRoleId in (select userrefrole.subRoleid from TawSystemUserRefRole as userrefrole where userrefrole.userid='" + userId + "'))" + " and (task.taskStatus=" + "2" + " or task.taskStatus=" + "8" + " or task.taskStatus=" + "1" + ")" + " and task.taskDisplayName<>'草稿' and task.taskDisplayName<>'阶段通知' and task.taskDisplayName<>'阶段回复' and task.operateType='dept' " + " and main.id=task.sheetKey and main.deleted<>'1'" + " ";
			StringBuffer hql = new StringBuffer(sql);
			if (length.intValue() != -1)
				if (!orderCondition.equals("") && orderCondition != null)
					hql.append("order by " + orderCondition);
				else
					hql.append("order by task.createTime desc");
			taskMap = getTaskDAO().getTaskListByCondition(hql.toString(), startIndex, length);
		}else{
			if(!"".equals(sheettype)){
				sql =" select distinct task.*,main.title as a  from localcommontask_task task,localcommontask_main main,localcommontask_link l where main.id = task.sheetkey and task.taskstatus in ('2','8') and task.processid =l.piid(+) and task.taskowner = l.operatedeptid(+) and (decode(l.operateuserid,'',task.taskowner,l.operateuserid) in ('"+userId+"','"+deptId+"')) and task.taskDisplayName <> '草稿' and task.taskDisplayName <> '阶段回复'  and main.deleted <>'1' and main.sheettype in ("+sheettype+") ";
				countsql=" select count(distinct(task.id)) from localcommontask_main main,localcommontask_task task,localcommontask_link l where main.id = task.sheetkey and task.taskstatus in ('2','8') and task.processid =l.piid(+) and task.taskowner = l.operatedeptid(+) and (decode(l.operateuserid,'',task.taskowner,l.operateuserid) in ('"+userId+"','"+deptId+"')) and task.taskDisplayName <> '草稿' and task.taskDisplayName <> '阶段回复'  and main.deleted <>'1' and main.sheettype in ("+sheettype+") ";
			}else{
				sql =" select distinct task.*,main.title as a  from localcommontask_task task,localcommontask_main main,localcommontask_link l where main.id = task.sheetkey and task.taskstatus in ('2','8') and task.processid =l.piid(+) and task.taskowner = l.operatedeptid(+) and (decode(l.operateuserid,'',task.taskowner,l.operateuserid) in ('"+userId+"','"+deptId+"')) and task.taskDisplayName <> '草稿' and task.taskDisplayName <> '阶段回复'  and main.deleted <>'1' ";
				countsql=" select count(distinct(task.id)) from localcommontask_main main,localcommontask_task task,localcommontask_link l where main.id = task.sheetkey and task.taskstatus in ('2','8') and task.processid =l.piid(+) and task.taskowner = l.operatedeptid(+) and (decode(l.operateuserid,'',task.taskowner,l.operateuserid) in ('"+userId+"','"+deptId+"')) and task.taskDisplayName <> '草稿' and task.taskDisplayName <> '阶段回复'  and main.deleted <>'1' ";
			}
			StringBuffer hql = new StringBuffer(sql);
			StringBuffer counthql = new StringBuffer(countsql);
			if (length.intValue() != -1){
				if (!orderCondition.equals("") && orderCondition != null){
					hql.append("order by " + orderCondition);
					counthql.append("order by " + orderCondition);
				}else{
					hql.append("order by task.createTime desc");
					counthql.append("order by task.createTime desc");
				}
			}
			Integer total = ((ILocalCommonTaskTaskDAO)getTaskDAO()).getCountTaskBySQL(counthql.toString());
			taskMap = ((ILocalCommonTaskTaskDAO)getTaskDAO()).getTaskListBySQL(hql.toString(), startIndex, length);
			taskMap.put("taskTotal", total);
		}
		return taskMap;
	}
	 
	 
 }