// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TeamListCommonFaultDisplaytagDecoratorHelper.java

package com.boco.eoms.sheet.commonfault.webapp.action;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dict.service.IDictService;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.sheet.base.task.ITask;
import java.util.Date;
import javax.servlet.jsp.PageContext;
import org.displaytag.decorator.TableDecorator;

public class TeamListCommonFaultDisplaytagDecoratorHelper extends TableDecorator
{

	public TeamListCommonFaultDisplaytagDecoratorHelper()
	{
	}

	public String getSheetId()
	{
		ITask task = (ITask)getCurrentRowObject();
		String url = StaticMethod.nullObject2String(getPageContext().getAttribute("url"));
		String sheetKey = StaticMethod.nullObject2String(task.getSheetKey()).trim();
		String taskId = StaticMethod.nullObject2String(task.getId()).trim();
		String taskName = StaticMethod.nullObject2String(task.getTaskName()).trim();
		String operateRoleId = StaticMethod.nullObject2String(task.getOperateRoleId()).trim();
		String TKID = StaticMethod.nullObject2String(task.getId()).trim();
		String piid = StaticMethod.nullObject2String(task.getProcessId()).trim();
		String taskStatus = StaticMethod.nullObject2String(task.getTaskStatus()).trim();
		String preLinkId = StaticMethod.nullObject2String(task.getPreLinkId()).trim();
		String sheetId = StaticMethod.nullObject2String(task.getSheetId()).trim();
		return "<a  href='" + url + "?method=showDetailPage&sheetKey=" + sheetKey + "&taskId=" + taskId + "&taskName=" + taskName + "&operateRoleId=" + operateRoleId + "&TKID=" + TKID + "&piid=" + piid + "&taskStatus=" + taskStatus + "&preLinkId=" + preLinkId + "&teamFlag=true" + "' >" + sheetId + "</a>";
	}

	public String getTaskDisplayName()
	{
		ITask task = (ITask)getCurrentRowObject();
		String taskName = StaticMethod.nullObject2String(task.getTaskDisplayName()).trim();
		String subTaskFlag = StaticMethod.nullObject2String(task.getSubTaskFlag()).trim();
		if (subTaskFlag != null && subTaskFlag.equals("true"))
			taskName = taskName + "(всхннЯ)";
		return taskName;
	}

	public Date getSendTime()
	{
		ITask task = (ITask)getCurrentRowObject();
		Date sendTime = task.getSendTime();
		return sendTime;
	}

	public String getTaskStatus()
	{
		ITask task = (ITask)getCurrentRowObject();
		String taskStatus = task.getTaskStatus();
		if (task.getIfWaitForSubTask().equals("true"))
			taskStatus = "-1";
		try
		{
			IDictService dictService = (IDictService)ApplicationContextHolder.getInstance().getBean("DictService");
			taskStatus = StaticMethod.nullObject2String(dictService.itemId2description(Util.constituteDictId("dict-sheet-common", "taskStatus"), taskStatus));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return taskStatus;
	}

	public String addRowClass()
	{
		String urlstr = "";
		ITask task = (ITask)getCurrentRowObject();
		String overtimeType = StaticMethod.nullObject2String(task.getOvertimeType());
		if (overtimeType.equals("1"))
			urlstr = "serious";
		else
		if (overtimeType.equals("2"))
			urlstr = "alert colorrow";
		return urlstr;
	}
}
