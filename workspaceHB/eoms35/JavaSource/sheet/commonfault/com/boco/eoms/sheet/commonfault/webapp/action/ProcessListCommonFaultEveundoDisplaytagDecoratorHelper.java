// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ProcessListCommonFaultDisplaytagDecoratorHelper.java

package com.boco.eoms.sheet.commonfault.webapp.action;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.webapp.action.ProcessListDisplaytagDecoratorHelper;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.jsp.PageContext;

public class ProcessListCommonFaultEveundoDisplaytagDecoratorHelper extends ProcessListDisplaytagDecoratorHelper
{

	public ProcessListCommonFaultEveundoDisplaytagDecoratorHelper()
	{
	}
	
	private String getUrl(Map taskMap){
    	String url = "";
    	
    	
    	return url;
    }
	
	public String getSheetId() {
		Map taskMap = (HashMap)getCurrentRowObject();
		String url = (String)getPageContext().getAttribute("url");
		String undoFlag = (String)getPageContext().getAttribute("undoFlag");
		System.out.println("getSheetId=undoFlag="+undoFlag);
		if (url == null)
			url = getUrl(taskMap);
		String sheetKey = StaticMethod.nullObject2String(taskMap.get("sheetKey")).trim();
		String taskName = StaticMethod.nullObject2String(taskMap.get("taskName")).trim();
		String id = StaticMethod.nullObject2String(taskMap.get("id")).trim();
		String operateRoleId = StaticMethod.nullObject2String(taskMap.get("operateRoleId")).trim();
		String TKID = StaticMethod.nullObject2String(taskMap.get("id")).trim();
		String processId = StaticMethod.nullObject2String(taskMap.get("processId")).trim();
		String taskStatus = StaticMethod.nullObject2String(taskMap.get("taskStatus")).trim();
		String preLinkId = StaticMethod.nullObject2String(taskMap.get("preLinkId")).trim();
		String sheetId = StaticMethod.nullObject2String(taskMap.get("sheetId")).trim();
		return "<a  href='" + url + "?method=showDetailPage&sheetKey=" + sheetKey + "&taskId=" + id + "&taskName=" + taskName + "&operateRoleId=" + operateRoleId + "&TKID=" + TKID + "&piid=" + processId + "&taskStatus=" + taskStatus + "&preLinkId=" + preLinkId + "&undoFlag=" + undoFlag + "' >" + sheetId + "</a>";
    }
	
	public String getId()
	{
		Map taskMap = (HashMap)getCurrentRowObject();
		Object object = getPageContext().getRequest().getAttribute("batchTaskMap");
		System.out.println("批量回复"+object);
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

	public String getTaskDisplayName()
	{
		Map taskMap = (HashMap)getCurrentRowObject();
		String name = StaticMethod.nullObject2String(taskMap.get("taskDisplayName"));
		if (name.equals("一级处理"))
			name = "T1处理";
		else
		if (name.equals("二级处理"))
			name = "T2处理";
		else
		if (name.equals("三级处理"))
			name = "T3处理";
		if (taskMap.get("subTaskFlag") != null && taskMap.get("subTaskFlag").equals("true"))
			name = name + "(子任务)";
		return name;
	}

	public String addRowClass()
	{
		Map taskMap = (HashMap)getCurrentRowObject();
		String mainFaultResponseLevel = StaticMethod.nullObject2String(taskMap.get("mainFaultResponseLevel"));
		String color = "";
		if (!mainFaultResponseLevel.equals(""))
			color = " jl-level-" + mainFaultResponseLevel;
		return super.addRowClass() + color;
	}
}
