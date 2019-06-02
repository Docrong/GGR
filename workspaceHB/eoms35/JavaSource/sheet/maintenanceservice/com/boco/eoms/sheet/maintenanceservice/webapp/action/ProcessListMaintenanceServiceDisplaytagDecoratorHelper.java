// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ProcessListCommonFaultDisplaytagDecoratorHelper.java

package com.boco.eoms.sheet.maintenanceservice.webapp.action;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.webapp.action.ProcessListDisplaytagDecoratorHelper;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.jsp.PageContext;

public class ProcessListMaintenanceServiceDisplaytagDecoratorHelper extends ProcessListDisplaytagDecoratorHelper
{

	public ProcessListMaintenanceServiceDisplaytagDecoratorHelper()
	{
	}

	public String getId()
	{
		Map taskMap = (HashMap)getCurrentRowObject();
		Object object = getPageContext().getRequest().getAttribute("batchTaskMap");
		System.out.println("ÅúÁ¿»Ø¸´"+object);
		String inputStr = "<input name=\"batchIds\" type=\"checkbox\" disabled=\"disabled\"/>";
		if (object != null)
		{
			HashMap taskNames = (HashMap)object;
			System.out.println(taskMap.get("taskName")+"========"+taskMap.get("subTaskFlag"));
			if (taskNames.get(taskMap.get("taskName")) != null && (taskMap.get("subTaskFlag") == null || taskMap.get("subTaskFlag").equals("false") || taskMap.get("subTaskFlag").equals("")))
				inputStr = "<input name=\"batchIds\" type=\"checkbox\" onclick=\"selectedSelf(this)\" value=\"" + taskMap.get("taskName") + "\" id=\"" + taskMap.get("id") + "\" operateRoleId=\"" + taskMap.get("operateRoleId") + "\" ifwaitforsubtask=\"" + taskMap.get("ifWaitForSubTask") + "\"/>";
		}
		return inputStr;
	}

	
}
