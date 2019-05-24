// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CommonFaultGroup.java

package com.boco.eoms.sheet.commonfault.model;

import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.commonfault.task.impl.CommonFaultTask;

public class CommonFaultGroup extends CommonFaultTask
{

	private String mainNetName;

	public CommonFaultGroup(ITask task, String mainNetName)
	{
		this.mainNetName = mainNetName;
		setAcceptTimeLimit(task.getAcceptTimeLimit());
		setCompleteTimeLimit(task.getCompleteTimeLimit());
		setCreateDay(task.getCreateDay());
		setCreateMonth(task.getCreateMonth());
		setCreateTime(task.getCreateTime());
		setCreateYear(task.getCreateYear());
		setCurrentLinkId(task.getCurrentLinkId());
		setFlowName(task.getFlowName());
		setId(task.getId());
		setIfWaitForSubTask(task.getIfWaitForSubTask());
		setOperateRoleId(task.getOperateRoleId());
		setOperateType(task.getOperateType());
		setOvertimeType(task.getOvertimeType());
		setParentTaskId(task.getParentTaskId());
		setPreLinkId(task.getPreLinkId());
		setProcessId(task.getProcessId());
		setSendTime(task.getSendTime());
		setSheetId(task.getSheetId());
		setSheetKey(task.getSheetKey());
		setSubTaskDealFalg(task.getSubTaskDealFalg());
		setSubTaskFlag(task.getSubTaskFlag());
		setTaskDisplayName(task.getTaskDisplayName());
		setTaskName(task.getTaskName());
		setTaskOwner(task.getTaskOwner());
		setTaskStatus(task.getTaskStatus());
		setTitle(task.getTitle());
		setPreDealDept(((CommonFaultTask)task).getPreDealDept());
		setPreDealUserId(((CommonFaultTask)task).getPreDealUserId());
	}

	public String getMainNetName()
	{
		return mainNetName;
	}

	public void setMainNetName(String mainNetName)
	{
		this.mainNetName = mainNetName;
	}
}
