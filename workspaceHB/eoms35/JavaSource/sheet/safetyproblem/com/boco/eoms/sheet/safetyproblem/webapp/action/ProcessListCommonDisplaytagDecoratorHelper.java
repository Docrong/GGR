// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ProcessListCommonDisplaytagDecoratorHelper.java

package com.boco.eoms.sheet.safetyproblem.webapp.action;

import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.webapp.action.ProcessListDisplaytagDecoratorHelper;
import java.util.HashMap;
import java.util.Map;

public class ProcessListCommonDisplaytagDecoratorHelper extends ProcessListDisplaytagDecoratorHelper
{

	public ProcessListCommonDisplaytagDecoratorHelper()
	{
	}

	public String getId()
	{
		Map taskMap = new HashMap();
		try
		{
			taskMap = (Map)getCurrentRowObject();
		}
		catch (Exception e)
		{
			ITask task = (ITask)getCurrentRowObject();
			taskMap = SheetBeanUtils.bean2Map(task);
		}
		String taskName = StaticMethod.nullObject2String(taskMap.get("taskName"));
		String sheetId = StaticMethod.nullObject2String(taskMap.get("sheetId"));
		String taskId = StaticMethod.nullObject2String(taskMap.get("id"));
		String inputStr = "<input name=\"batchIds\" type=\"checkbox\" disabled=\"disabled\"/>";
		if (taskName.equals("AffirmHumTask"))
			inputStr = "<input name=\"batchIds\" type=\"checkbox\" onclick=\"selectedSelf(this)\" value=\"" + sheetId + "\" id=\"" + taskId + "\" sheetId=\"" + sheetId + "\"/>";
		return inputStr;
	}
}
