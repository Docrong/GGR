// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CommonFaultTaskManagerImpl.java

package com.boco.eoms.sheet.commonfault.service.impl;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.dao.ITaskDAO;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.impl.TaskServiceImpl;
import com.boco.eoms.sheet.base.util.*;
import com.boco.eoms.sheet.commonfault.dao.ICommonFaultTaskDAO;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultTaskManager;
import com.boco.eoms.sheet.overtimetip.util.OvertimeTipUtil;
import java.io.PrintStream;
import java.util.*;

public class CommonFaultTaskManagerImpl extends TaskServiceImpl
	implements ICommonFaultTaskManager
{

	public CommonFaultTaskManagerImpl()
	{
	}

	public HashMap getAcceptTaskByRole(Map condition, String userId, Integer startIndex, Integer length)
		throws Exception
	{
		HashMap taskMap = new HashMap();
		String orderCondition = (String)condition.get("orderCondition");
		condition.put("userId", userId);
		String sql = QuerySqlInit.getAcceptTaskByRole(condition);
		StringBuffer hql = new StringBuffer();
		if (!orderCondition.equals("") && orderCondition != null)
			hql.append(sql).append(" order by " + orderCondition);
		else
			hql.append(sql).append(" order by task.createTime desc");
		System.out.println("sql=======" + hql);
		taskMap = getTaskDAO().getTaskListByCondition(hql.toString(), startIndex, length);
		return taskMap;
	}

	public List getCommonfaultTasksByCondition(String condition)
		throws Exception
	{
		String hql = " from " + getTaskModelObject().getClass().getName() + " task,com.boco.eoms.sheet.commonfault.model.CommonFaultMain main" + " where task.sheetKey=main.id and " + condition + " order by createTime";
		return getTaskDAO().getTasksByHql(hql);
	}

	public HashMap getUndoTaskByOverTime(Map condition, String userId, String deptId, String flowName, Integer startIndex, Integer length)
		throws Exception
	{
		String wheresql = "";
		HashMap taskMap = new HashMap();
		HashMap cloumnMap = OvertimeTipUtil.getMainColumnByMapping(flowName);
		Iterator it = cloumnMap.keySet().iterator();
		String ifNetOpt = "";
		System.out.println("---cloumnMap---" + cloumnMap);
		String ruternColumnName;
		String tmpOvertimeClm;
		for (ruternColumnName = ""; it.hasNext(); ruternColumnName = ruternColumnName + " main." + (String)cloumnMap.get(tmpOvertimeClm) + ",")
			tmpOvertimeClm = (String)it.next();

		if (!ruternColumnName.equals(""))
			ruternColumnName = "," + ruternColumnName.substring(0, ruternColumnName.length() - 1);
		String sql = "select task.* " + ruternColumnName + ",main.mainNetName from commonfault_task task,commonfault_main main where main.sendTime>sysdate-30 and main.id = task.sheetkey and task.taskDisplayName<>'草稿' and main.deleted<>'1' and main.status=0 and (main.mainTownerFlag<>'1' or main.mainTownerFlag is null) ";
		String countsql = "select count(task.id)  from commonfault_task task,commonfault_main main where main.sendTime>sysdate-30 and main.id = task.sheetkey and task.taskDisplayName<>'草稿' and main.deleted<>'1' and main.status=0 and (main.mainTownerFlag<>'1' or main.mainTownerFlag is null) ";
		if (condition.containsKey("ifNetOpt"))
		{
			ifNetOpt = SheetStaticMethod.nullObject2String(condition.get("ifNetOpt").toString());
			if ("1".equals(ifNetOpt))
				wheresql = wheresql + " and main.netOptimization ='1'";
			else
			if ("3".equals(ifNetOpt))
				wheresql = wheresql + " and (main.netOptimization = '3' or main.netOptimization is null)";
		} else
		{
			wheresql = wheresql + " and (main.netOptimization in ('1','3') or main.netOptimization is null)";
		}
		if (!condition.get("ifAgent").toString().equals(""))
			if (condition.get("ifAgent").toString().equals("1"))
				wheresql = wheresql + " and decode(main.ifAgent ,'', '1',main.ifAgent) =1";
			else
				wheresql = wheresql + " and main.ifAgent='" + condition.get("ifAgent").toString() + "'";
	 	wheresql = wheresql + " and  exists (select 1 from ( select t3.id from commonfault_task t3,commonfault_main m2 where taskstatus in ('2','8') and ifwaitforsubtask = 'false' and t3.sheetkey = m2.id and m2.status=0 and m2.deleted <>'1' and taskowner = '" + userId + "'" + " union all select t3.id from commonfault_task t3,commonfault_main m2 where taskstatus in ('2','8') and ifwaitforsubtask = 'false' and t3.sheetkey = m2.id and m2.status=0 and m2.deleted <>'1' and taskowner = '" + deptId + "'" + " union all select t3.id from commonfault_task t3,commonfault_main m2,taw_system_userrefrole r where taskstatus in ('2','8') and ifwaitforsubtask = 'false' and t3.sheetkey = m2.id and m2.status=0 and m2.deleted <>'1' and r.subRoleid = t3.taskowner and r.userid='" + userId + "'" + " union all select t1.id from commonfault_task t1, commonfault_task t2, commonfault_main m1 where t1.ifwaitforsubtask = 'true' and t1.taskstatus in ('2', '8') and t1.id = t2.parenttaskid and t2.taskstatus = 5 and t2.subtaskdealfalg is null and t1.sheetkey = m1.id and m1.status = 0 and m1.deleted <> '1' and t1.taskowner='" + userId + "'" + " union all select t1.id from commonfault_task t1, commonfault_task t2, commonfault_main m1 where t1.ifwaitforsubtask = 'true' and t1.taskstatus in ('2', '8') and t1.id = t2.parenttaskid and t2.taskstatus = 5 and t2.subtaskdealfalg is null and t1.sheetkey = m1.id and m1.status = 0 and m1.deleted <> '1' and t1.taskowner='" + deptId + "'" + " union all select t1.id from commonfault_task t1, commonfault_task t2, commonfault_main m1, taw_system_userrefrole r where t1.ifwaitforsubtask = 'true' and t1.taskstatus in ('2', '8') and t1.id = t2.parenttaskid and t2.taskstatus = 5 and t2.subtaskdealfalg is null and t1.sheetkey = m1.id and t1.taskowner=r.subRoleid and m1.status = 0 and m1.deleted <> '1' and r.userid = '" + userId + "') t where t.id = task.id)";
		sql = sql + wheresql;
		countsql = countsql + wheresql;
		
		if (condition.get("sheetId") != null)
		{
			sql = sql + wheresql + "and  task.sheetid='" + condition.get("sheetId") + "'";
			countsql = countsql + wheresql + "and  task.sheetid='" + condition.get("sheetId") + "'";
		}
		
		if (condition.get("sheetIdMain") != null && !condition.get("sheetIdMain").toString().equals(""))
		{
			sql = sql +  "and  task.sheetid like '%" + condition.get("sheetIdMain") + "%'";
			countsql = countsql  + "and  task.sheetid like '%" + condition.get("sheetIdMain") + "%'";
		}
		
		if (condition.get("title") != null && !condition.get("title").toString().equals(""))
		{
			sql = sql +  "and  main.title like '%" + condition.get("title") + "%'";
			countsql = countsql +  "and  main.title like '%" + condition.get("title") + "%'";
		}
		
		if (condition.get("completeTimeLimitStartDate") != null && !condition.get("completeTimeLimitStartDate").toString().equals(""))
		{
			sql = sql +  " and  TASK.COMPLETETIMELIMIT >= TO_DATE('"+condition.get("completeTimeLimitStartDate")+"', 'yyyy-MM-dd HH24:mi:ss')";
			countsql = countsql +  " and  TASK.COMPLETETIMELIMIT >= TO_DATE('"+condition.get("completeTimeLimitStartDate")+"', 'yyyy-MM-dd HH24:mi:ss')";
		}
		
		if (condition.get("completeTimeLimitEndDate") != null && !condition.get("completeTimeLimitEndDate").toString().equals(""))
		{
			sql = sql +  " and  TASK.COMPLETETIMELIMIT <= TO_DATE('"+condition.get("completeTimeLimitEndDate")+"', 'yyyy-MM-dd HH24:mi:ss')";
			countsql = countsql +  " and  TASK.COMPLETETIMELIMIT <= TO_DATE('"+condition.get("completeTimeLimitEndDate")+"', 'yyyy-MM-dd HH24:mi:ss')";
		}
		
		if (condition.get("showArea") != null && !condition.get("showArea").toString().equals(""))
		{
			sql = sql +  " and  MAIN.TODEPTID IN ('"+condition.get("showArea")+"')";
			countsql = countsql +  " and  MAIN.TODEPTID IN ('"+condition.get("showArea")+"')";
		}
		
		if (condition.get("stepId") != null && !condition.get("stepId").toString().equals(""))
		{
			sql = sql +  " and  TASK.TASKNAME = '"+condition.get("stepId")+"'";
			countsql = countsql +  " and  TASK.TASKNAME = '"+condition.get("stepId")+"'";
		}
		
		if (condition.get("mainNetSortOne") != null && !condition.get("mainNetSortOne").toString().equals(""))
		{
			sql = sql +  "and  main.mainNetSortOne = '" + condition.get("mainNetSortOne") + "'";
			countsql = countsql +  "and  main.mainNetSortOne = '" + condition.get("mainNetSortOne") + "'";
		}
		
		if (condition.get("mainNetSortTwo") != null && !condition.get("mainNetSortTwo").toString().equals(""))
		{
			sql = sql +  "and  main.mainNetSortTwo = '" + condition.get("mainNetSortTwo") + "'";
			countsql = countsql + "and  main.mainNetSortTwo = '" + condition.get("mainNetSortTwo") + "'";
		}
		
		if (condition.get("mainNetSortThree") != null && !condition.get("mainNetSortThree").toString().equals(""))
		{
			sql = sql +  "and  main.mainNetSortThree = '" + condition.get("mainNetSortThree") + "'";
			countsql = countsql +  "and  main.mainNetSortThree = '" + condition.get("mainNetSortThree") + "'";
		}
		
		
		
		String orderCondition = (String)condition.get("orderCondition");
		if (length.intValue() != -1)
			if (!orderCondition.equals("") && orderCondition != null)
			{
				sql = sql + "order by " + orderCondition;
				countsql = countsql + "order by " + orderCondition;
			} else
			{
				sql = sql + "order by task.createTime desc";
				countsql = countsql + "order by task.createTime desc";
			}
		System.out.println("lizhi--countsql=" + countsql);
		System.out.println("lyg===sql="+sql);
		Integer total = ((ICommonFaultTaskDAO)getTaskDAO()).getCountTaskBySQL(countsql);
		taskMap = ((ICommonFaultTaskDAO)getTaskDAO()).getTaskListBySQL(sql, startIndex, length);
		taskMap.put("taskTotal", total);
		return taskMap;
	}

	public HashMap getUndoTaskByOverTimeByNet(Map condition, String userId, String deptId, String flowName, Integer startIndex, Integer length)
		throws Exception
	{
		String wheresql = "";
		HashMap taskMap = new HashMap();
		HashMap cloumnMap = OvertimeTipUtil.getMainColumnByMapping(flowName);
		Iterator it = cloumnMap.keySet().iterator();
		System.out.println("---cloumnMap---" + cloumnMap);
		String ruternColumnName;
		String tmpOvertimeClm;
		for (ruternColumnName = ""; it.hasNext(); ruternColumnName = ruternColumnName + " main." + (String)cloumnMap.get(tmpOvertimeClm) + ",")
			tmpOvertimeClm = (String)it.next();

		if (!ruternColumnName.equals(""))
			ruternColumnName = "," + ruternColumnName.substring(0, ruternColumnName.length() - 1);
		String sql = "select task.* " + ruternColumnName + ",main.mainNetName from commonfault_task task,commonfault_main main where main.id = task.sheetkey and task.taskDisplayName<>'草稿' and main.deleted<>'1' and main.status=0 and main.netOptimization='2' ";
		String countsql = "select count(task.id)  from commonfault_task task,commonfault_main main where main.id = task.sheetkey and task.taskDisplayName<>'草稿' and main.deleted<>'1' and main.status=0 and main.netOptimization ='2'";
		if (!condition.get("ifAgent").toString().equals(""))
			if (condition.get("ifAgent").toString().equals("1"))
				wheresql = wheresql + " and decode(main.ifAgent ,'', '1',main.ifAgent) =1";
			else
				wheresql = wheresql + " and main.ifAgent='" + condition.get("ifAgent").toString() + "'";
		wheresql = wheresql + " and exists(select 1 from ( select t3.id from commonfault_task t3,commonfault_main m2 where taskstatus in ('2','8') and ifwaitforsubtask = 'false' and t3.sheetkey = m2.id and m2.status=0 and m2.deleted <>'1' and taskowner = '" + userId + "'" + " union all select t3.id from commonfault_task t3,commonfault_main m2 where taskstatus in ('2','8') and ifwaitforsubtask = 'false' and t3.sheetkey = m2.id and m2.status=0 and m2.deleted <>'1' and taskowner = '" + deptId + "'" + " union all select t3.id from commonfault_task t3,commonfault_main m2,taw_system_userrefrole r where taskstatus in ('2','8') and ifwaitforsubtask = 'false' and t3.sheetkey = m2.id and m2.status=0 and m2.deleted <>'1' and r.subRoleid = t3.taskowner and r.userid='" + userId + "'" + " union all select t1.id from commonfault_task t1, commonfault_task t2, commonfault_main m1 where t1.ifwaitforsubtask = 'true' and t1.taskstatus in ('2', '8') and t1.id = t2.parenttaskid and t2.taskstatus = 5 and t2.subtaskdealfalg is null and t1.sheetkey = m1.id and m1.status = 0 and m1.deleted <> '1' and t1.taskowner='" + userId + "'" + " union all select t1.id from commonfault_task t1, commonfault_task t2, commonfault_main m1 where t1.ifwaitforsubtask = 'true' and t1.taskstatus in ('2', '8') and t1.id = t2.parenttaskid and t2.taskstatus = 5 and t2.subtaskdealfalg is null and t1.sheetkey = m1.id and m1.status = 0 and m1.deleted <> '1' and t1.taskowner='" + deptId + "'" + " union all select t1.id from commonfault_task t1, commonfault_task t2, commonfault_main m1, taw_system_userrefrole r where t1.ifwaitforsubtask = 'true' and t1.taskstatus in ('2', '8') and t1.id = t2.parenttaskid and t2.taskstatus = 5 and t2.subtaskdealfalg is null and t1.sheetkey = m1.id and t1.taskowner=r.subRoleid and m1.status = 0 and m1.deleted <> '1' and r.userid = '" + userId + "') t where t.id = task.id) ";
		sql = sql + wheresql;
		countsql = countsql + wheresql;
		if (condition.get("sheetId") != null)
		{
			sql = sql + wheresql + "and  task.sheetid='" + condition.get("sheetId") + "'";
			countsql = countsql + wheresql + "and  task.sheetid='" + condition.get("sheetId") + "'";
		}
		String orderCondition = (String)condition.get("orderCondition");
		if (length.intValue() != -1)
			if (!orderCondition.equals("") && orderCondition != null)
			{
				sql = sql + "order by " + orderCondition;
				countsql = countsql + "order by " + orderCondition;
			} else
			{
				sql = sql + "order by task.createTime desc";
				countsql = countsql + "order by task.createTime desc";
			}
		System.out.println("lizhi--countsql=" + countsql);
		Integer total = ((ICommonFaultTaskDAO)getTaskDAO()).getCountTaskBySQL(countsql);
		taskMap = ((ICommonFaultTaskDAO)getTaskDAO()).getTaskListBySQL(sql, startIndex, length);
		taskMap.put("taskTotal", total);
		return taskMap;
	}

	public HashMap getUndoTaskSql(Map condition, String flowName)
	{
		String userId = (String)condition.get("userId");
		String deptId = (String)condition.get("deptId");
		HashMap cloumnMap = OvertimeTipUtil.getAllMainColumnByMapping(flowName);
		HashMap entityMap = new HashMap();
		HashMap returnMap = new HashMap();
		Iterator it = cloumnMap.keySet().iterator();
		String ruternColumnName = "";
		String tmpOvertimeClm;
		for (; it.hasNext(); entityMap.put(tmpOvertimeClm, "m" + tmpOvertimeClm))
		{
			tmpOvertimeClm = (String)it.next();
			ruternColumnName = ruternColumnName + " main." + (String)cloumnMap.get(tmpOvertimeClm) + " as m" + tmpOvertimeClm + ",";
		}

		if (!ruternColumnName.equals(""))
			ruternColumnName = "," + ruternColumnName.substring(0, ruternColumnName.length() - 1);
		String hql = "select task.*" + ruternColumnName + " from " + " commonfault_task  task, commonfault_main   main " + "where task.id in ( ( select id from commonfault_task task1 where " + "( task1.taskOwner='" + userId + "' or task1.taskOwner='" + deptId + "'" + " or task1.taskOwner in (select userrefrole.subRoleid from taw_system_userrefrole  userrefrole where userrefrole.userid='" + userId + "'))" + " and (task1.taskStatus='" + "2" + "'" + " or task1.taskStatus='" + "8" + "'" + " or task1.taskStatus='" + "1" + "')" + " and task1.taskDisplayName<>'草稿' and task1.ifWaitForSubTask = 'false') union " + " ( select id from commonfault_task task1 " + " where task1.ifWaitForSubTask='true' and ( task1.id in (" + " select task2.parentTaskId from commonfault_task  task2 " + " where task2.subTaskFlag='true' and (task2.subTaskDealFalg='false' or task2.subTaskDealFalg is null) " + " and task2.taskStatus='" + "5" + "'))" + " and ( task1.taskOwner='" + userId + "' or task1.taskOwner='" + deptId + "' " + " or task1.taskOwner in (select userrefrole.subRoleid from taw_system_userrefrole  userrefrole where userrefrole.userid='" + userId + "'))" + " and (task1.taskStatus='" + "2" + "'" + " or task1.taskStatus='" + "8" + "'" + " or task1.taskStatus='" + "1" + "')" + " and task1.taskDisplayName<>'草稿'" + " )) and main.id=task.sheetKey and main.deleted<>'1'";
		returnMap.put("hsql", hql);
		returnMap.put("entityMap", entityMap);
		return returnMap;
	}

	public HashMap getAllTaskByUserid(Map condition, String userId, String deptId, String flowName, Integer startIndex, Integer length)
		throws Exception
	{
		HashMap taskMap = new HashMap();
		BaseMain mainObject = (BaseMain)condition.get("mainObject");
		String hql = "select distinct task from " + getTaskModelObject().getClass().getName() + " as task," + mainObject.getClass().getName() + " as main " + "where ((task.taskOwner='" + userId + "' or task.taskOwner='" + deptId + "')" + " or task.taskOwner in (select userrefrole.subRoleid from TawSystemUserRefRole as userrefrole where userrefrole.userid='" + userId + "'))" + " and (task.taskStatus=" + "2" + " or task.taskStatus=" + "8" + " or task.taskStatus=" + "1" + ")" + " and task.taskDisplayName<>'草稿'" + " and main.id=task.sheetKey and main.deleted<>'1'" + " and (task.ifWaitForSubTask='false' or (task.ifWaitForSubTask='true' and task.id in (select task1.parentTaskId from " + getTaskModelObject().getClass().getName() + " as task1 where task1.subTaskFlag='true' and (task1.subTaskDealFalg='false' or task1.subTaskDealFalg is null ) and task1.taskStatus='" + "5" + "')))" + " order by task.createTime desc";
		taskMap = ((ICommonFaultTaskDAO)getTaskDAO()).getTasksomelengthListBySQL(hql, startIndex, length);
		return taskMap;
	}

	public HashMap getNoAcceptTaskByUserid(Map condition, String userId, String deptId, String flowName, Integer startIndex, Integer length)
		throws Exception
	{
		HashMap taskMap = new HashMap();
		BaseMain mainObject = (BaseMain)condition.get("mainObject");
		String hql = "select distinct task from " + getTaskModelObject().getClass().getName() + " as task," + mainObject.getClass().getName() + " as main " + "where ((task.taskOwner='" + userId + "' or task.taskOwner='" + deptId + "')" + " or task.taskOwner in (select userrefrole.subRoleid from TawSystemUserRefRole as userrefrole where userrefrole.userid='" + userId + "'))" + " and (task.taskStatus=" + "2" + " )" + " and task.taskDisplayName<>'草稿'" + " and main.id=task.sheetKey and main.deleted<>'1'" + " and (task.ifWaitForSubTask='false' or (task.ifWaitForSubTask='true' and task.id in (select task1.parentTaskId from " + getTaskModelObject().getClass().getName() + " as task1 where task1.subTaskFlag='true' and (task1.subTaskDealFalg='false' or task1.subTaskDealFalg is null ) and task1.taskStatus='" + "5" + "')))" + " order by task.createTime desc";
		taskMap = ((ICommonFaultTaskDAO)getTaskDAO()).getTasksomelengthListBySQL(hql, startIndex, length);
		return taskMap;
	}

	public HashMap getNoDealTaskByUserid(Map condition, String userId, String deptId, String flowName, Integer startIndex, Integer length)
		throws Exception
	{
		HashMap taskMap = new HashMap();
		BaseMain mainObject = (BaseMain)condition.get("mainObject");
		String hql = "select distinct task from " + getTaskModelObject().getClass().getName() + " as task," + mainObject.getClass().getName() + " as main " + "where ((task.taskOwner='" + userId + "' or task.taskOwner='" + deptId + "')" + " or task.taskOwner in (select userrefrole.subRoleid from TawSystemUserRefRole as userrefrole where userrefrole.userid='" + userId + "'))" + " and (task.taskStatus=" + "8" + " )" + " and task.taskDisplayName<>'草稿'" + " and main.id=task.sheetKey and main.deleted<>'1'" + " and (task.ifWaitForSubTask='false' or (task.ifWaitForSubTask='true' and task.id in (select task1.parentTaskId from " + getTaskModelObject().getClass().getName() + " as task1 where task1.subTaskFlag='true' and (task1.subTaskDealFalg='false' or task1.subTaskDealFalg is null ) and task1.taskStatus='" + "5" + "')))" + " order by task.createTime desc";
		taskMap = ((ICommonFaultTaskDAO)getTaskDAO()).getTasksomelengthListBySQL(hql, startIndex, length);
		return taskMap;
	}

	public HashMap setTaskModel(Object task, HashMap processTemplateMap, HashMap taskBOMap, HashMap taskObjectMap, HashMap mainObjectMap, HashMap linkObjectMap)
		throws Exception
	{
		HashMap valueMap = new HashMap();
		String taskPropNames = HibernateConfigurationHelper.getPkColumnName(task.getClass()) + "," + HibernateConfigurationHelper.getPropNamesWithoutPK(task.getClass());
		String taskPropName[] = taskPropNames.split(",");
		for (int i = 0; taskPropName.length > 0 && i < taskPropName.length; i++)
		{
			String name = taskPropName[i];
			PropertyFile propertyFile = PropertyFile.getInstance(getTaskConfigPath());
			String key = SheetStaticMethod.nullObject2String(PropertyFile.getPropertyByName(name));
			System.out.println("key=" + key);
			if (key.equals(""))
			{
				Object taskObjectTemp = taskObjectMap.get(name);
				valueMap.put(name, taskObjectTemp);
			} else
			if (key.indexOf("task.") >= 0)
			{
				key = key.substring(key.lastIndexOf(".") + 1, key.length());
				Object taskBOTemp = taskBOMap.get(key);
				valueMap.put(name, taskBOTemp);
			} else
			if (key.indexOf("process.") >= 0)
			{
				key = key.substring(key.lastIndexOf(".") + 1, key.length());
				Object processTemp = processTemplateMap.get(key);
				valueMap.put(name, processTemp);
			} else
			if (key.indexOf("main.") >= 0)
			{
				key = key.substring(key.lastIndexOf(".") + 1, key.length());
				Object processTemp = mainObjectMap.get(key);
				valueMap.put(name, processTemp);
			} else
			if (key.indexOf("link.") >= 0)
			{
				key = key.substring(key.lastIndexOf(".") + 1, key.length());
				Object processTemp = linkObjectMap.get(key);
				valueMap.put(name, processTemp);
			} else
			if (key.indexOf("#") >= 0)
			{
				String expressions[] = key.split("\\?");
				if (expressions.length > 1)
				{
					String leftExpression = expressions[0];
					String rightExpression = expressions[1];
					String rightExprs[] = rightExpression.split(":");
					if (leftExpression.indexOf("taskBO.") >= 0)
					{
						int endNumber = leftExpression.indexOf("==");
						String objectName = leftExpression.substring(leftExpression.lastIndexOf(".") + 1, endNumber).trim();
						Object taskBOTemp = taskBOMap.get(objectName);
						String taskBOTempValue = SheetStaticMethod.nullObject2String(taskBOTemp);
						String real = leftExpression.substring(endNumber, leftExpression.length());
						String realExpression = "\"" + taskBOTempValue + "\"" + real;
						if (EomsBshEval.getbooleanFromStringExpression(realExpression))
						{
							if (rightExprs.length > 1)
							{
								String rightOne = rightExprs[0].trim();
								if (rightOne.indexOf(".") == -1)
								{
									Object taskObjectTemp = taskObjectMap.get(name);
									valueMap.put(name, taskObjectTemp);
								}
							}
						} else
						if (rightExprs.length > 1)
						{
							String rightTwo = rightExprs[1];
							if (rightTwo.indexOf(".") >= 0)
							{
								String rightTwoName = rightTwo.substring(rightTwo.indexOf(".") + 1, rightTwo.length()).trim();
								Object taskObjectTemp = taskBOMap.get(rightTwoName);
								valueMap.put(name, taskObjectTemp);
							}
						}
					}
				}
			} else
			if (key.indexOf("&") >= 0)
			{
				System.out.println("taskBO.description&linkBO.operateType:" + key);
				String expressions[] = key.split("&");
				if (expressions.length > 1)
				{
					String leftExpression = expressions[0];
					String rightExpression = expressions[1];
					leftExpression = leftExpression.substring(leftExpression.lastIndexOf(".") + 1, leftExpression.length());
					System.out.println("leftExpression:" + leftExpression);
					String description = StaticMethod.nullObject2String(taskBOMap.get(leftExpression));
					System.out.println("description:" + description);
					rightExpression = rightExpression.substring(rightExpression.lastIndexOf(".") + 1, rightExpression.length());
					System.out.println("rightExpression:" + rightExpression);
					String operateType = StaticMethod.nullObject2String(linkObjectMap.get(rightExpression));
					System.out.println("operateType:" + operateType);
					System.out.println("name:" + name);
					String linkFalg = StaticMethod.nullObject2String(linkObjectMap.get("linkFalg"));
					System.out.println("linkFalg==lyg=="+linkFalg);
					if ((description.equals("FirstExcuteHumTask") && (operateType.equals("0") || operateType.equals("4"))) || "33".equals(linkFalg))
						valueMap.put(name, operateType);
				}
			}
		}

		for (Iterator it = valueMap.keySet().iterator(); it.hasNext();)
		{
			String key = it.next().toString();
			System.out.println("task model name=" + key);
			Object obj = valueMap.get(key);
			if (obj != null)
				System.out.println("task mode value=" + obj.toString());
			else
				System.out.println("task mode value is null");
		}

		return valueMap;
	}

	public HashMap getTaskListByCondition(String queryStr, Integer curPage, Integer pageSize)
	throws Exception
{
	return ((ICommonFaultTaskDAO)getTaskDAO()).getTasksListBySQL(queryStr, curPage, pageSize);
}

	public HashMap getEeveundo(String queryStr, Integer curPage, Integer pageSize)
	throws Exception
{
	return ((ICommonFaultTaskDAO)getTaskDAO()).getEeveundo(queryStr, curPage, pageSize);
}

	
	
}
